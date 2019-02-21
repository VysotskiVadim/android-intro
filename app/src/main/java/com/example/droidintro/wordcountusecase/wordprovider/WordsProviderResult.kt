package com.example.droidintro.wordcountusecase.wordprovider

import com.example.droidintro.ProcessingProgress

sealed class WordProviderResult
//TODO: get rid of
data class PartialResult(val progress: ProcessingProgress, val words:Collection<String>) : WordProviderResult()