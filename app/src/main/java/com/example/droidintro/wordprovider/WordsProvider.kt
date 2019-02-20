package com.example.droidintro.wordprovider

import com.example.droidintro.TextSource
import io.reactivex.Flowable

interface WordsProvider {
    fun getWords(wordSource:TextSource): Flowable<WordProviderResult>
}