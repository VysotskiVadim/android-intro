package com.example.droidintro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.droidintro.App
import com.example.droidintro.R
import com.example.droidintro.wordcountusecase.DaggerCountWordsInTextComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

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
        initializeResultView()

        viewModel = ViewModelProviders.of(this, mainScreenViewModelFactory).get(MainViewModel::class.java)
        bind()
    }

    private fun bind() {
        viewModel.screenState.observe(this, Observer<MainScreenState> { onNewState(it ?: ErrorState("State isn't initialized")) })
        go_button.setOnClickListener { viewModel.go() }
    }

    private fun onNewState(state:MainScreenState) {
        error_message.visibility = View.GONE
        go_button.visibility = View.GONE
        results.visibility = View.GONE
        progress.visibility = View.GONE
        when (state) {
            is ReadyToGo -> go_button.visibility = View.VISIBLE
            is ErrorState -> {
                error_message.text = state.message
                error_message.visibility = View.VISIBLE
            }
            is ProcessingInProgress -> progress.visibility = View.VISIBLE
            is ProcessingCompleted -> {
                results.visibility = View.VISIBLE
                results.adapter = WordsRecyclerViewAdapter(state.result)
            }
        }
    }

    private fun initializeResultView() {
        results.setHasFixedSize(true)
        results.layoutManager =  LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        if (!viewModel.back()) {
            super.onBackPressed()
        }

    }
}