package com.example.droidintro.ui

import com.example.droidintro.AppComponent
import com.example.droidintro.wordcountusecase.CountWordsInTextComponent
import dagger.Component

@Component(dependencies = [AppComponent::class, CountWordsInTextComponent::class])
interface MainScreenComponent {
    fun mainScreenViewModel():MainViewModel;
}
