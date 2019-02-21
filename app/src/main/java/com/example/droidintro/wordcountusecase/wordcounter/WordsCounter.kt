package com.example.droidintro.wordcountusecase.wordcounter

import com.example.droidintro.ProcessingProgress
import com.example.droidintro.wordcountusecase.wordprovider.WordProviderResult
import io.reactivex.Flowable

interface WordsCounter {
    fun countWords(words:Flowable<WordProviderResult>):Flowable<WordsCounterResult>
}

sealed class WordsCounterResult
data class WordsCounterInProgress(val progress:ProcessingProgress) : WordsCounterResult()
//TODO: use pagination for result
data class WordsCounterProcessingCompleted(val result:Collection<Word>) : WordsCounterResult()

data class Word(val value:String, var count:Int)
