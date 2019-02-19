package com.example.droidintro

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.util.regex.Pattern

fun splitTextChunksByWords(chunks:Flowable<String>):Flowable<Collection<String>> {
    val accumulated = chunks.scan(LastValueAccumulator(listOf(), ""), BiFunction(::textToWords))
    val last = accumulated.lastElement().filter { it.lastWord.isNotEmpty() }.map { listOf(it.lastWord) }
    return accumulated.map { it.result }.mergeWith(last)
}

private object Constants {
    val wordPattern = Pattern.compile("\\w+")!!
}

private data class LastValueAccumulator(val result:Collection<String>, val lastWord:String)

private fun textToWords(accumulator:LastValueAccumulator, text:String):LastValueAccumulator {
    val result = mutableListOf<String>()
    val matcher = Constants.wordPattern.matcher(text)
    if (accumulator.lastWord.isNotEmpty() && matcher.find() && matcher.start() == 0) {
        result.add(accumulator.lastWord + matcher.group())
    }
    while (matcher.find()) {
        result.add( matcher.group())
    }
    val lastWord = if (result.isNotEmpty()) result.removeAt(result.size - 1) else ""
    return LastValueAccumulator(result, lastWord)
}