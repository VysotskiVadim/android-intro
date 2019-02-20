package com.example.droidintro.wordprovider

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.WordsSource
import org.reactivestreams.Publisher

class WordsProviderImplementation : WordsProvider {

    override fun getWords(wordSource: WordsSource): Publisher<WordProviderResult> {
        when (wordSource) {
            is DownloadFromInternet -> throw NotImplementedError()
            else -> throw NotImplementedError()
        }
    }

}