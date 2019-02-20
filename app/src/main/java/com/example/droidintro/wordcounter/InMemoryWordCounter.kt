package com.example.droidintro.wordcounter

import io.reactivex.Flowable

class InMemoryWordsCounter : WordsCounter {
    override fun countWords(words: Flowable<Collection<String>>): Flowable<WordsCounterResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}