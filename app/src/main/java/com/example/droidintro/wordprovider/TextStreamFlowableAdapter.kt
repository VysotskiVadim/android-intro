package com.example.droidintro.wordprovider

import com.example.droidintro.ProcessingProgress
import io.reactivex.Emitter
import io.reactivex.Flowable
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.Callable

fun textInputToFlowable(input:InputStream, chunkSize:Int):Flowable<WordProviderResult> {
    val buffer = CharArray(chunkSize)
    return Flowable.generate<WordProviderResult, InputStreamReader>(
        Callable<InputStreamReader> { InputStreamReader(input) },
        BiConsumer { input:InputStreamReader, emitter:Emitter<WordProviderResult> ->
            val readed = input.read(buffer)
            when {
                readed <= 0 -> emitter.onComplete()
                else -> emitter.onNext(PartialResult(ProcessingProgress(0f), listOf(String(buffer, 0, readed)))) //TODO: implement splitting here
            }
        },
        Consumer<InputStreamReader> { stream:InputStreamReader -> stream.close() }
    )
}