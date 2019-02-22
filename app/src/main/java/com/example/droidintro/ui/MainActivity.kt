package com.example.droidintro.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.droidintro.App
import com.example.droidintro.R
import com.example.droidintro.wordcountusecase.DaggerCountWordsInTextComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.component.inject(this)
        setContentView(R.layout.activity_main)
        val mViewModel = ViewModelProviders.of(this, mainScreenViewModelFactory).get(MainViewModel::class.java)
    }

    val app:App
        get() = application as App

    private val mainScreenViewModelFactory:MainScreenViewModelFactory by lazy {
        MainScreenViewModelFactory(DaggerMainScreenComponent.builder()
            .countWordsInTextComponent(DaggerCountWordsInTextComponent.builder().build())
            .appComponent(app.component).build())
    }
}
