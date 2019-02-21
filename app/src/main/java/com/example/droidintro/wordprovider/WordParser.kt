package com.example.droidintro.wordprovider

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.util.regex.Pattern

fun Flowable<String>.splitTextChunksByWords():Flowable<Collection<String>> {
    val wordsFromText = this.scan(LastValueAccumulator(listOf(), ""), BiFunction(::textToWords))
    val accumulatedWord = wordsFromText.lastElement().filter { it.lastWord.isNotEmpty() }.map { listOf(it.lastWord) }
    return wordsFromText.map { it.result }.mergeWith(accumulatedWord)
}

private object Constants {
    val wordPattern = Pattern.compile("\\w+")!!
}

private data class LastValueAccumulator(val result:Collection<String>, val lastWord:String)

private fun textToWords(accumulator: LastValueAccumulator, chunkOfText:String): LastValueAccumulator {
    val result = mutableListOf<String>()
    val matcher = Constants.wordPattern.matcher(chunkOfText)
    var lastWordPosition = -2
    if (accumulator.lastWord.isNotEmpty()){
        if (matcher.find()) {
            lastWordPosition = matcher.end()
            if (matcher.start() == 0) {
                result.add(accumulator.lastWord + matcher.group())
            }
            else {
                result.add(accumulator.lastWord)
                result.add(matcher.group())
            }
        }
        else {
            result.add(accumulator.lastWord)
        }

    }

    while (matcher.find()) {
        result.add( matcher.group())
        lastWordPosition = matcher.end()
    }

    val isLastWordProbablyInTheNextChunk = lastWordPosition == chunkOfText.length
    val lastWord = if (result.isNotEmpty() && isLastWordProbablyInTheNextChunk) result.removeAt(result.size - 1) else ""
    return LastValueAccumulator(result, lastWord)
}