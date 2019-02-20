package com.example.droidintro.countwordusecase

import com.example.droidintro.textprovider.TextProviderModule
import com.example.droidintro.wordcounter.WordCounterModule
import com.example.droidintro.wordcountusecase.CountWordsInTextComponent
import com.example.droidintro.wordprovider.WordsProviderModule
import dagger.Component

@Component(modules = [
    TextProviderModule::class,
    WordsProviderModule::class,
    WordCounterModule::class,
    TextProviderModule::class
])
interface CountWordUseCaseTestComponent : CountWordsInTextComponent {

}