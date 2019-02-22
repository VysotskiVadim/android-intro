package com.example.droidintro.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.wordcountusecase.CountWordsInTextUseCase
import com.example.droidintro.wordcountusecase.wordcounter.Word
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterInProgress
import com.example.droidintro.wordcountusecase.wordcounter.WordsCounterProcessingCompleted
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val counter: CountWordsInTextUseCase) : ViewModel() {

    private val state:MutableLiveData<MainScreenState> = MutableLiveData()
    init {
        state.postValue(ReadyToGo())
    }

    val screenState:LiveData<MainScreenState> get() = state

    fun go() {
        if (state.value is ReadyToGo) {
            state.postValue(ProcessingInProgress())
            counter.countWords(DownloadFromInternet("http://www.loyalbooks.com/download/text/Railway-Children-by-E-Nesbit.txt"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.single())
                .subscribe({
                    when (it) {
                        is WordsCounterInProgress -> {
                            //TODO: implement procegress ui
                        }
                        is WordsCounterProcessingCompleted -> {
                            state.postValue(ProcessingCompleted(it.result))
                        }
                    }
                }, {
                    state.postValue(ErrorState(it.message ?: "unknown error"))
                })
        }
    }
}

sealed class MainScreenState
class ReadyToGo : MainScreenState()
data class ErrorState(val message:String) : MainScreenState()
class ProcessingInProgress : MainScreenState()
data class ProcessingCompleted(val result:List<Word>) : MainScreenState()