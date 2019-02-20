package com.example.droidintro.wordprovider

import com.example.droidintro.textprovider.Text
import io.reactivex.Flowable

interface WordsProvider {
    fun getWords(text:Text): Flowable<WordProviderResult>
}