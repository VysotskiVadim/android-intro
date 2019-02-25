package com.example.droidintro

import com.example.droidintro.wordcountusecase.wordcounter.CountWordsInMemoryOperator
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterProcessingCompleted
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterResult
import com.example.droidintro.wordcountusecase.wordprovider.PartialResult
import com.example.droidintro.wordcountusecase.wordprovider.WordProviderResult
import io.reactivex.Emitter
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.assertEquals
import org.junit.Test

class InMemoryWordsCounterTest {
    @Test
    fun addition_isCorrect() {
        val input = Flowable.generate<WordProviderResult, Int>({ 0 }, ::tenWords)
        val testSubscriber = TestSubscriber<WordsCounterResult>()

        input.lift(CountWordsInMemoryOperator()).subscribe(testSubscriber)

        testSubscriber.assertComplete()
        val result: WordsCounterProcessingCompleted = testSubscriber.values().last() as WordsCounterProcessingCompleted
        assertEquals(1, result.result.size)
        assertEquals("haha", result.result.first().value)
        assertEquals(10, result.result.first().count) 
    }
}

fun tenWords(state:Int, emitter:Emitter<WordProviderResult>):Int {
    if (state == 10) {
        emitter.onComplete()
    }
    else {
        emitter.onNext(PartialResult(ProcessingProgress(0f), listOf("haha")))
    }
    return state + 1
}
