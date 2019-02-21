package com.example.droidintro

import com.example.droidintro.wordprovider.splitTextChunksByWords
import io.reactivex.BackpressureStrategy
import io.reactivex.Emitter
import io.reactivex.Flowable
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
            arrayOf( listOf("test text"), arrayOf("test", "text")),
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
        val iterator = input.iterator()
        val chunks = Flowable.generate( {iterator}, fun ( state:Iterator<String>, emitter: Emitter<String>):Unit {
            if (state.hasNext()) {
                emitter.onNext(state.next())
            }
            else {
                emitter.onComplete()
            }
        })
        val testSubscriber = TestSubscriber<Collection<String>>()
        //act
        chunks.splitTextChunksByWords()
            .subscribe(testSubscriber)
        //assert
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        Assert.assertArrayEquals(expected, testSubscriber.values().flatten().toTypedArray())
    }
}