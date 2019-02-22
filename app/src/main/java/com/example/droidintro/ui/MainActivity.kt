package com.example.droidintro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.droidintro.App
import com.example.droidintro.R
import com.example.droidintro.wordcountusecase.DaggerCountWordsInTextComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val app:App
        get() = application as App

    private val mainScreenViewModelFactory:MainScreenViewModelFactory by lazy {
        MainScreenViewModelFactory(DaggerMainScreenComponent.builder()
            .countWordsInTextComponent(DaggerCountWordsInTextComponent.builder().build())
            .appComponent(app.component).build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.component.inject(this)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, mainScreenViewModelFactory).get(MainViewModel::class.java)
        bind(viewModel)
    }

    private fun bind(viewModel:MainViewModel) {
        viewModel.screenState.observe(this, Observer<MainScreenState> { onNewState(it ?: ErrorState("State isn't initialized")) })
        go_button.setOnClickListener { viewModel.go() }
    }

    private fun onNewState(state:MainScreenState) {
        error_message.visibility = View.GONE
        go_button.visibility = View.GONE
        when (state) {
            is ReadyToGo -> go_button.visibility = View.VISIBLE
            is ErrorState -> {
                error_message.text = state.message
                error_message.visibility = View.VISIBLE
            }
        }
    }
}