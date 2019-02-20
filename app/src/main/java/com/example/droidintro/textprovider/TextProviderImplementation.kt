package com.example.droidintro.textprovider

import com.example.droidintro.TextSource
import io.reactivex.Single

class TextProviderImplementation : TextProvider {
    override fun getText(source: TextSource): Single<Text> {
        TODO("use http ok here") //To change body of created functions use File | Settings | File Templates.
    }

}