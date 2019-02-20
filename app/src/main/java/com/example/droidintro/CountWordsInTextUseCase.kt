package com.example.droidintro

import com.example.droidintro.textprovider.TextProvider
import com.example.droidintro.wordcounter.WordsCounter
import com.example.droidintro.wordcounter.WordsCounterResult
import com.example.droidintro.wordprovider.WordProviderResult
import com.example.droidintro.wordprovider.WordsProvider
import io.reactivex.Flowable

class CountWordsInTextUseCase(
    private val textProvider: TextProvider,
    private val wordsProvider:WordsProvider,
    private val wordsCounter:WordsCounter
) {
    fun countWords(source:TextSource):Flowable<WordsCounterResult> {
        val words:Flowable<WordProviderResult> = textProvider.getText(source).map { wordsProvider.getWords(it) }.flatMapPublisher { it }
        return wordsCounter.countWords(words)
    }
}