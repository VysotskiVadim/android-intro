package com.example.droidintro

import io.reactivex.Flowable


fun splitTextChunksByWords(chunks:Flowable<String>):Flowable<Collection<String>> {
    return chunks.map { listOf(it) }
}