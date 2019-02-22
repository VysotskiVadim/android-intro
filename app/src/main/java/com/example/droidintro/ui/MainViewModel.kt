package com.example.droidintro.ui

import android.arch.lifecycle.ViewModel
import com.example.droidintro.wordcountusecase.CountWordsInTextUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(counter: CountWordsInTextUseCase) : ViewModel() {

}