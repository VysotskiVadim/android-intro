package com.example.droidintro

import android.app.Application
import com.example.droidintro.wordcountusecase.DaggerCountWordsInTextComponent

class App : Application() {

    val component: AppComponent =
        DaggerAppComponent
            .builder()
            .countWordsInTextComponent(DaggerCountWordsInTextComponent.builder().build())
            .build()

}

