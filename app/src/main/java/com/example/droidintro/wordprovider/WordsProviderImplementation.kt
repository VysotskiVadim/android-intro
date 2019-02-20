package com.example.droidintro.wordprovider

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.TextSource
import io.reactivex.Flowable

class WordsProviderImplementation : WordsProvider {

    override fun getWords(wordSource: TextSource): Flowable<WordProviderResult> {
        when (wordSource) {
            is DownloadFromInternet -> throw NotImplementedError()
            else -> throw NotImplementedError()
        }
    }

}