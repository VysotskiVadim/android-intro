package com.example.droidintro.countwordusecase

import com.example.droidintro.wordcountusecase.wordcounter.WordCounterModule
import com.example.droidintro.wordcountusecase.CountWordsInTextComponent
import com.example.droidintro.wordcountusecase.wordprovider.WordsProviderModule
import dagger.Component

@Component(modules = [
    TextProviderTestModule::class,
    WordsProviderModule::class,
    WordCounterModule::class
])
interface CountWordUseCaseTestComponent : CountWordsInTextComponent {
}