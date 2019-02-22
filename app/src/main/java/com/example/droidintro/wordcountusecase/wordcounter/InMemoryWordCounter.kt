package com.example.droidintro.wordcountusecase.wordcounter

import com.example.droidintro.wordcountusecase.wordprovider.PartialResult
import com.example.droidintro.wordcountusecase.wordprovider.WordProviderResult
import io.reactivex.FlowableOperator
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class InMemoryWordsCounter @Inject constructor() : WordsCounter {
    override fun countWordsOperator(): FlowableOperator<WordsCounterResult, WordProviderResult> {
        return CountWordsInMemoryOperator()
    }
}

class CountWordsInMemoryOperator : FlowableOperator<WordsCounterResult, WordProviderResult> {

    override fun apply(subscriber: Subscriber<in WordsCounterResult>): Subscriber<in WordProviderResult> {
        return WordCounterWrapper(subscriber)
    }

    class WordCounterWrapper(private val results: Subscriber<in WordsCounterResult>) : FlowableSubscriber<WordProviderResult>, Subscription {

        private val wordToUsage = HashMap<String, Int>()
        private var subscription : Subscription? = null

        override fun onSubscribe(s: Subscription) {
            subscription = s
            results.onSubscribe(this)
        }

        override fun cancel() {
            subscription?.cancel()
        }

        override fun request(n: Long) {
            subscription?.request(n)
        }

        override fun onNext(t: WordProviderResult?) {
            if (t == null) return
            when (t) {
                is PartialResult -> {
                    applyWordsPack(t.words)
                    results.onNext(WordsCounterInProgress(t.progress))
                }
            }
        }

        override fun onError(t: Throwable?) {
            results.onError(t)
        }

        override fun onComplete() {
            results.onNext(WordsCounterProcessingCompleted(wordToUsage.map { Word(it.key, it.value) }))
            results.onComplete()
        }

        private fun applyWordsPack(words:Collection<String>) {
            for (word in words) {
                if (wordToUsage.contains(word)) {
                    wordToUsage[word] = wordToUsage[word]!!.plus(1)
                }
                else {
                    wordToUsage[word] = 1
                }
            }
        }
    }
}