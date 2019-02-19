package com.example.droidintro

import io.reactivex.Flowable
import java.util.regex.Pattern

private object Constants {
    val wordPattern = Pattern.compile("\\w+")!!
}

fun splitTextChunksByWords(chunks:Flowable<String>):Flowable<Collection<String>> {
    return chunks.map(::textToWords)
}

private fun textToWords(text:String):Collection<String> {
    val result = mutableListOf<String>()
    val matcher = Constants.wordPattern.matcher(text)
    while (matcher.find()) {
        result.add(matcher.group())
    }
    return result
}