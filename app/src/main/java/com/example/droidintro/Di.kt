package com.example.droidintro

import com.example.droidintro.ui.MainActivity
import com.example.droidintro.wordcountusecase.CountWordsInTextComponent
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Module
class AppModule() {
}

@Component(
    modules = [ AppModule::class ]
)
interface AppComponent {
    fun inject(activity:MainActivity)
}
