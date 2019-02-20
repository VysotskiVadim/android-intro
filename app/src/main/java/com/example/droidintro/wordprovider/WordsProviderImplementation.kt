package com.example.droidintro.wordprovider

import com.example.droidintro.textprovider.Text
import com.example.droidintro.textprovider.TextInStream
import io.reactivex.Flowable
import javax.inject.Inject

class WordsProviderImplementation @Inject constructor() : WordsProvider {
    override fun getWords(text: Text): Flowable<WordProviderResult> {
        return when(text) {
            is TextInStream -> textInputToFlowable(text.stream, 500)
        }
    }
}