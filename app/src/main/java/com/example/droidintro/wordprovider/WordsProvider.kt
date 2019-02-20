package com.example.droidintro.wordprovider

import com.example.droidintro.WordsSource
import org.reactivestreams.Publisher

interface WordsProvider {
    fun getWords(wordSource:WordsSource): Publisher<WordProviderResult>
}