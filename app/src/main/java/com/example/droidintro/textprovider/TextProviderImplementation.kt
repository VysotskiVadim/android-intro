package com.example.droidintro.textprovider

import com.example.droidintro.TextSource
import io.reactivex.Single
import javax.inject.Inject

class TextProviderImplementation @Inject constructor() : TextProvider {
    override fun getText(source: TextSource): Single<Text> {
        TODO("use http ok here") //To change body of created functions use File | Settings | File Templates.
    }

}