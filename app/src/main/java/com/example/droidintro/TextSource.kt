package com.example.droidintro

sealed class TextSource
data class DownloadFromInternet(val url:String) : TextSource()