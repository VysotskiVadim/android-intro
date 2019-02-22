package com.example.droidintro.wordcountusecase.wordcounter

import com.example.droidintro.ProcessingProgress
import com.example.droidintro.wordcountusecase.wordprovider.WordProviderResult
import io.reactivex.FlowableOperator

interface WordsCounter {
    fun countWordsOperator(): FlowableOperator<WordsCounterResult, WordProviderResult>
}

data class Word(val value:String, var count:Long) {
    val isPrime:Boolean = count.isPrimeNumber()
}

sealed class WordsCounterResult
data class WordsCounterInProgress(val progress:ProcessingProgress) : WordsCounterResult()
//TODO: use pagination for result?
data class WordsCounterProcessingCompleted(val result:List<Word>) : WordsCounterResult()


