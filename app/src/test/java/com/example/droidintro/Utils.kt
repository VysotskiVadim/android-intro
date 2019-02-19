package com.example.droidintro

import java.io.ByteArrayInputStream
import java.io.InputStream

fun inputFromText(text:String):InputStream {
    return ByteArrayInputStream(text.toByteArray())
}