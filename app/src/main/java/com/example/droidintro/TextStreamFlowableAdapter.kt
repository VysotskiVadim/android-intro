package com.example.droidintro

import io.reactivex.Emitter
import io.reactivex.Flowable
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.Callable

fun textInputToFlowable(input:InputStream, chunkSize:Int):Flowable<String> {
    val buffer = CharArray(chunkSize)
    return Flowable.generate<String, InputStreamReader>(
        Callable<InputStreamReader> { InputStreamReader(input) },
        BiConsumer { input:InputStreamReader, emitter:Emitter<String> ->
            val readed = input.read(buffer)
            when {
                readed <= 0 -> emitter.onComplete()
                else -> emitter.onNext(String(buffer, 0, readed))
            }
        },
        Consumer<InputStreamReader> { stream:InputStreamReader -> stream.close() }
    )
}