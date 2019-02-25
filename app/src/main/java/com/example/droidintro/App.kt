package com.example.droidintro

import android.app.Application

class App : Application() {

    val component: AppComponent =
        DaggerAppComponent
            .builder()
            .build()

}

