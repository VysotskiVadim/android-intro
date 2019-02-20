package com.example.droidintro

import com.example.droidintro.wordcounter.WordsCounter
import com.example.droidintro.wordcounter.WordsCounterResult
import com.example.droidintro.wordprovider.WordsProvider
import io.reactivex.Flowable

class CountWordsInTextUseCase(
    private val wordsProvider:WordsProvider,
    private val wordsCounter:WordsCounter
) {
    fun countWords(source:TextSource):Flowable<WordsCounterResult> {
        return wordsCounter.countWords(wordsProvider.getWords(source))
    }
}