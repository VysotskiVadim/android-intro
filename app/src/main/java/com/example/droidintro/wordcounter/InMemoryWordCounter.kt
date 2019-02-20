package com.example.droidintro.wordcounter

import com.example.droidintro.wordprovider.WordProviderResult
import io.reactivex.Flowable

class InMemoryWordsCounter : WordsCounter {
    override fun countWords(words: Flowable<WordProviderResult>): Flowable<WordsCounterResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}