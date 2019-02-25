package com.example.droidintro.wordcountusecase.textprovider

import com.example.droidintro.TextSource
import io.reactivex.Single
import java.io.InputStream

interface TextProvider {
    fun getText(source:TextSource): Single<Text>
}

sealed class Text
data class TextInStream(val stream:InputStream) : Text()

