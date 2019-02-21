package com.example.droidintro.countwordusecase

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.any
import com.example.droidintro.inputFromText
import com.example.droidintro.textprovider.Text
import com.example.droidintro.textprovider.TextInStream
import com.example.droidintro.textprovider.TextProvider
import com.example.droidintro.wordcounter.Word
import com.example.droidintro.wordcounter.WordsCounterProcessingCompleted
import com.example.droidintro.wordcounter.WordsCounterResult
import com.example.droidintro.wordcountusecase.CountWordsInTextUseCase
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.junit.Assert.*

class CountWordsUseCaseTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    @Mock lateinit var textProvider:TextProvider

    lateinit var useCase:CountWordsInTextUseCase


    @Before
    fun setup() {
        useCase = DaggerCountWordUseCaseTestComponent.builder()
            .textProviderTestModule(TextProviderTestModule(textProvider))
            .build().getUseCase()
    }

    @Test
    fun test() {
        val expectedResultSortedByWords = arrayOf(
            Word("text", 1),
            Word("test", 2)
        )
        Mockito.`when`(textProvider.getText(any())).thenReturn(Single.just<Text>(TextInStream(inputFromText("test text test"))))
        val subscriber = TestSubscriber<WordsCounterResult>()

        useCase.countWords(DownloadFromInternet("")).subscribe(subscriber)

        subscriber.assertComplete()
        val result:WordsCounterProcessingCompleted = subscriber.values().last() as WordsCounterProcessingCompleted
        assertArrayEquals( expectedResultSortedByWords , result.result.sortedByDescending { it.value }.toTypedArray())
    }
}