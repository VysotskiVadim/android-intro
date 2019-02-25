package com.example.droidintro.wordcountusecase.wordprovider

import com.example.droidintro.wordcountusecase.wordprovider.badimplementation.WordsProviderImplementation
import dagger.Module
import dagger.Provides

@Module
class WordsProviderModule {
    @Provides fun getWordsProvider(implementation: WordsProviderImplementation):WordsProvider = implementation
}