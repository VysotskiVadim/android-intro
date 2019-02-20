package com.example.droidintro.wordprovider

import dagger.Module
import dagger.Provides

@Module
class WordsProviderModule {
    @Provides fun getWordsProvider(implementation:WordsProviderImplementation):WordsProvider = implementation
}