package com.example.droidintro.wordprovider

import com.example.droidintro.ProcessingProgress

sealed class WordProviderResult
data class PartialResult(val progress: ProcessingProgress, val words:Collection<String>) : WordProviderResult()