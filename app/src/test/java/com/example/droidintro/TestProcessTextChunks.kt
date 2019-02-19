package com.example.droidintro

import io.reactivex.BackpressureStrategy
import io.reactivex.rxkotlin.Flowables
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class TestProcessTextChunks(private val input:Collection<String>, private val expected:Array<String>) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf( listOf("word"), arrayOf("word")),
            arrayOf( listOf("first second"), arrayOf("first", "second")),
            arrayOf( listOf("first seco", "nd third"), arrayOf("first", "second", "third")),
            arrayOf( listOf("first second ", "third"), arrayOf("first", "second", "third")),
            arrayOf( listOf("first", " s","econd third"), arrayOf("first", "second", "third")),
            arrayOf( listOf("first second", " third"), arrayOf("first", "second", "third"))
        )
    }


    @Test
    fun testProcessTextSplitByChunks() {
        //arrange
        val chunks = Flowables.create<String>(BackpressureStrategy.BUFFER) {
            for (i in input) {
                it.onNext(i)
            }
            it.onComplete()
        }
        val testSubscriber = TestSubscriber<Collection<String>>()
        //act
        splitTextChunksByWords(chunks).subscribe(testSubscriber)
        //assert
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        Assert.assertArrayEquals(testSubscriber.values().flatten().toTypedArray(), expected)
    }
}