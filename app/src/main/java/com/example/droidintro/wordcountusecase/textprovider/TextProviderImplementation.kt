package com.example.droidintro.wordcountusecase.textprovider

import com.example.droidintro.TextSource
import io.reactivex.Single
import javax.inject.Inject

class TextProviderImplementation @Inject constructor() : TextProvider {
    override fun getText(source: TextSource): Single<Text> {
        return Single.error(NotImplementedError("Http ok part not implemented"))
    }

}