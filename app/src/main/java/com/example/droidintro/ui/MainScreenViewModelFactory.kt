package com.example.droidintro.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class MainScreenViewModelFactory (private val mainScreenComponent:MainScreenComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mainScreenComponent.mainScreenViewModel() as T
        }
        throw NotImplementedError()
    }
}