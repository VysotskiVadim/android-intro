package com.example.droidintro.wordcountusecase

import com.example.droidintro.textprovider.TextProviderModule
import com.example.droidintro.wordcounter.WordCounterModule
import com.example.droidintro.wordprovider.WordsProviderModule
import dagger.Component

@Component(modules = [
    TextProviderModule::class,
    WordsProviderModule::class,
    WordCounterModule::class
])
interface CountWordsInTextComponent {
    fun getUseCase():CountWordsInTextUseCase
}