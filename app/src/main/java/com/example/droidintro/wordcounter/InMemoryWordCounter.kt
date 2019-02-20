package com.example.droidintro.wordcounter

import com.example.droidintro.wordprovider.WordProviderResult
import io.reactivex.Flowable
import javax.inject.Inject

class InMemoryWordsCounter @Inject constructor() : WordsCounter {
    override fun countWords(words: Flowable<WordProviderResult>): Flowable<WordsCounterResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}