package com.example.droidintro

import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.Assert.*

class TestInputFlowableAdapter {
    @Test
    fun testInputToFlowable() {
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
}
