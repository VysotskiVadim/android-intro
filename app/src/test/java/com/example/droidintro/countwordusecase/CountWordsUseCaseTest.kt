package com.example.droidintro.countwordusecase

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.wordcounter.WordsCounterResult
import com.example.droidintro.wordcountusecase.DaggerCountWordsInTextComponent
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

class CountWordsUseCaseTest {
    @Test
    fun test() {
        val countWordsInTextUseCase = DaggerCountWordsInTextComponent.create().getUseCase()
        val subscriber = TestSubscriber<WordsCounterResult>()
        countWordsInTextUseCase.countWords(DownloadFromInternet("doesn't matter what is here")).subscribe(subscriber)
    }
}