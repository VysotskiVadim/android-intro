package com.example.droidintro.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.droidintro.App
import com.example.droidintro.R
import com.example.droidintro.wordcountusecase.CountWordsInTextUseCase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var counter:CountWordsInTextUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        setContentView(R.layout.activity_main)
    }
}
