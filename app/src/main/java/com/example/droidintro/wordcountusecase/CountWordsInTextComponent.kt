package com.example.droidintro.wordcountusecase

import com.example.droidintro.wordcountusecase.textprovider.TextProviderModule
import com.example.droidintro.wordcountusecase.wordcounter.WordCounterModule
import com.example.droidintro.wordcountusecase.wordprovider.WordsProviderModule
import dagger.Subcomponent

@Subcomponent(modules = [
    TextProviderModule::class,
    WordsProviderModule::class,
    WordCounterModule::class
])
interface CountWordsInTextComponent {
    fun getUseCase():CountWordsInTextUseCase
}