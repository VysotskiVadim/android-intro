package com.example.droidintro.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.droidintro.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
