package com.example.droidintro.wordcounter

import com.example.droidintro.wordprovider.PartialResult
import com.example.droidintro.wordprovider.WordProviderResult
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class InMemoryWordsCounter @Inject constructor() : WordsCounter {
    override fun countWords(words: Flowable<WordProviderResult>): Flowable<WordsCounterResult> {
        return countWordsInternal(words)
    }
}

fun countWordsInternal(words: Flowable<WordProviderResult>): Flowable<WordsCounterResult> {
    val wordToUsage = HashMap<String, Int>()
    val result:Maybe<WordsCounterResult> = words.lastElement().map {
        when (it) {
            is PartialResult -> countWordsTo(it.words, wordToUsage)
        }
        WordsCounterProcessingCompleted(wordToUsage.map { Word(it.key, it.value) })
    }
    return words.skipLast(1).doOnNext {
            when(it) {
                is PartialResult -> countWordsTo(it.words, wordToUsage)
            }
        }
        .map {
            when(it) {
                is PartialResult -> WordsCounterInProgress(it.progress) as WordsCounterResult //TODO: remove cast workaround
            }
        }
        .mergeWith(result)
}

private fun countWordsTo(words:Collection<String>, map:MutableMap<String, Int>) {
    for (word in words) {
        if (map.contains(word)) {
            map[word] = map[word]!!.plus(1)
        }
        else {
            map[word] = 1
        }
    }
}