package com.example.droidintro.wordcountusecase

import com.example.droidintro.TextSource
import com.example.droidintro.wordcountusecase.textprovider.TextProvider
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounter
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterResult
import com.example.droidintro.wordcountusecase.wordprovider.WordProviderResult
import com.example.droidintro.wordcountusecase.wordprovider.WordsProvider
import io.reactivex.Flowable
import javax.inject.Inject

class CountWordsInTextUseCase @Inject constructor(
    private val textProvider: TextProvider,
    private val wordsProvider:WordsProvider,
    private val wordsCounter:WordsCounter
) {
    fun countWords(source: TextSource):Flowable<WordsCounterResult> {
        val words:Flowable<WordProviderResult> = textProvider.getText(source).map { wordsProvider.getWords(it) }.flatMapPublisher { it }
        return wordsCounter.countWords(words)
    }
}