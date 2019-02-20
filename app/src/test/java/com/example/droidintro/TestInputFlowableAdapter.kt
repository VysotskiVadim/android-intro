package com.example.droidintro

import com.example.droidintro.wordprovider.PartialResult
import com.example.droidintro.wordprovider.WordProviderResult
import com.example.droidintro.wordprovider.textInputToFlowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.Assert.*

class TestInputFlowableAdapter {
    @Test
    fun testInputToFlowable() {
        //arrange
        val input = inputFromText("testdata x")
        val testSubscriber = TestSubscriber<WordProviderResult>()

        //act
        textInputToFlowable(input, 4).subscribe(testSubscriber)

        //assert
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(3)
        assertArrayEquals(testSubscriber.values().map { when(it) { is PartialResult -> it.words.first() } }.toTypedArray(), arrayOf("test", "data", " x")) //TODO: remove workaround
    }
}
