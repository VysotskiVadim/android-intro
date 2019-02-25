package com.example.droidintro.wordcountusecase.wordprovider

import com.example.droidintro.wordcountusecase.textprovider.Text
import io.reactivex.Flowable

interface WordsProvider {
    fun getWords(text:Text): Flowable<WordProviderResult>
}