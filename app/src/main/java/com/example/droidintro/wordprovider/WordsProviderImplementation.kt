package com.example.droidintro.wordprovider

import com.example.droidintro.textprovider.Text
import io.reactivex.Flowable
import javax.inject.Inject

class WordsProviderImplementation @Inject constructor() : WordsProvider {
    override fun getWords(text: Text): Flowable<WordProviderResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}