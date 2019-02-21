package com.example.droidintro.wordcountusecase.wordprovider

import com.example.droidintro.ProcessingProgress
import com.example.droidintro.wordcountusecase.textprovider.Text
import com.example.droidintro.wordcountusecase.textprovider.TextInStream
import io.reactivex.Flowable
import javax.inject.Inject

class WordsProviderImplementation @Inject constructor() : WordsProvider {

    //TODO: move to config or decide in runtime ?
    private val BUFFER_SIZE = 512 * 1024;

    override fun getWords(text: Text): Flowable<WordProviderResult> {
        return when(text) {
            is TextInStream -> textInputToFlowable(text.stream, BUFFER_SIZE)
                .splitTextChunksByWords() //TODO: get rid of splitTextChunksByWords and process char buffer in parallel
                .map { PartialResult(ProcessingProgress(0f), it) } //TODO: implement progress
        }
    }
}