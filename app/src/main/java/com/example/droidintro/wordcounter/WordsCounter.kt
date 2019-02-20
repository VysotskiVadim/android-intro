package com.example.droidintro.wordcounter

import com.example.droidintro.ProcessingProgress
import io.reactivex.Flowable

interface WordsCounter {
    fun countWords(words:Flowable<Collection<String>>):Flowable<WordsCounterResult>
}

sealed class WordsCounterResult
data class WordsCounterInProgress(val progress:ProcessingProgress) : WordsCounterResult()
data class WordsCounterProcessingCompleted(val result:Collection<Word>) : WordsCounterResult()

data class Word(val value:String, var count:Int)
