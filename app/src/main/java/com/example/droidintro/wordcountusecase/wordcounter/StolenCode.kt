package com.example.droidintro.wordcountusecase.wordcounter

//source: https://www.programiz.com/kotlin-programming/examples/prime-number
fun Long.isPrimeNumber():Boolean {
    var i = 2
    var flag = false
    while (i <= this / 2) {
        // condition for nonprime number
        if (this % i == 0L) {
            flag = true
            break
        }
        ++i
    }

    return flag
}