package com.example.droidintro.countwordusecase

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.any
import com.example.droidintro.inputFromText
import com.example.droidintro.wordcountusecase.textprovider.Text
import com.example.droidintro.wordcountusecase.textprovider.TextInStream
import com.example.droidintro.wordcountusecase.textprovider.TextProvider
import com.example.droidintro.wordcountusecase.wordcounter.Word
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterProcessingCompleted
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterResult
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
        //arrange
        val expectedResultSortedByWords = arrayOf(
            Word("text", 1),
            Word("test", 2)
        )
        Mockito.`when`(textProvider.getText(any())).thenReturn(Single.just<Text>(TextInStream(inputFromText("test text test"))))
        val subscriber = TestSubscriber<WordsCounterResult>()
        //act
        useCase.countWords(DownloadFromInternet("")).subscribe(subscriber)
        //assert
        subscriber.assertComplete()
        val lastValue = subscriber.values().last();
        assertTrue("last value should be WordsCounterProcessingCompleted", lastValue is WordsCounterProcessingCompleted)
        val result:WordsCounterProcessingCompleted = lastValue as WordsCounterProcessingCompleted
        assertArrayEquals( expectedResultSortedByWords , result.result.sortedByDescending { it.value }.toTypedArray())
    }
}