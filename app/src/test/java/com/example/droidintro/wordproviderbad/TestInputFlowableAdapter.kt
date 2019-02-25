package com.example.droidintro.wordproviderbad

import com.example.droidintro.inputFromText
import com.example.droidintro.wordcountusecase.wordprovider.badimplementation.textInputToFlowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.Assert.*

class TestInputFlowableAdapter {
    @Test
    fun testInputToFlowableSmallBuffer() {
        //arrange
        val input = inputFromText("testdata x")
        val testSubscriber = TestSubscriber<String>()

        //act
        textInputToFlowable(input, 4).subscribe(testSubscriber)

        //assert
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(3)
        assertArrayEquals(testSubscriber.values().toTypedArray(), arrayOf("test", "data", " x"))
    }

    @Test
    fun testInputToFlowableBufferBiggerThenText() {
        //arrange
        val input = inputFromText("testdata x")
        val testSubscriber = TestSubscriber<String>()

        //act
        textInputToFlowable(input, 500).subscribe(testSubscriber)

        //assert
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertArrayEquals(testSubscriber.values().toTypedArray(), arrayOf("testdata x"))
    }
}
