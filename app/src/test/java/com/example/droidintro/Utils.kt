package com.example.droidintro

import org.mockito.Mockito
import java.io.ByteArrayInputStream
import java.io.InputStream

fun <T> any(): T = Mockito.any<T>()

fun inputFromText(text:String):InputStream {
    return ByteArrayInputStream(text.toByteArray())
}