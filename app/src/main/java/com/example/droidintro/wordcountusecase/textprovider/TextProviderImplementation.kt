package com.example.droidintro.wordcountusecase.textprovider

import com.example.droidintro.DownloadFromInternet
import com.example.droidintro.TextSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import javax.inject.Inject

class TextProviderImplementation @Inject constructor() : TextProvider {

    private val httpClient:OkHttpClient by lazy {
        OkHttpClient()
    }

    override fun getText(source: TextSource): Single<Text> {
        return when (source) {
            is DownloadFromInternet -> downloadUsingHttpOK(source.url).subscribeOn(Schedulers.io())
        }
    }

    private fun downloadUsingHttpOK(url:String):Single<Text> {
        return Single.create {
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            try {
                httpClient.newCall(request).execute().use { responce ->
                    val stream = responce.body()?.byteStream()
                    if (stream != null) {
                        it.onSuccess(TextInStream(stream))
                    }
                    else {
                        it.onError(IOException("Stream is empty"))
                    }
                }
            } catch (e: IOException) {
                it.onError(e)
            }
        }
    }
}