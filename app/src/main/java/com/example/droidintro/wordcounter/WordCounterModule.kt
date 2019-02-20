package com.example.droidintro.wordcounter

import dagger.Module
import dagger.Provides

@Module
class WordCounterModule {
    @Provides fun getWordCounter(implementation:InMemoryWordsCounter):WordsCounter = implementation
}