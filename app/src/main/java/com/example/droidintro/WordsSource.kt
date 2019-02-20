package com.example.droidintro

sealed class WordsSource
data class DownloadFromInternet(val url:String) : WordsSource()