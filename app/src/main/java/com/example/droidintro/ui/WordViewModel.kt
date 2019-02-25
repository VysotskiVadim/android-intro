package com.example.droidintro.ui

import com.example.droidintro.wordcountusecase.wordcounter.Word

class WordViewModel(word: Word) {
    val value:String = word.value
    val count:String = word.count.toString() + if (word.isPrime) "(prime)" else ""
}