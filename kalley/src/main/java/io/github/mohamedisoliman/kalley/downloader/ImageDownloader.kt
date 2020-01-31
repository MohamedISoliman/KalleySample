package io.github.mohamedisoliman.kalley.downloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.mohamedisoliman.kalley.Cache
import io.github.mohamedisoliman.kalley.network.Dispatcher
import io.github.mohamedisoliman.kalley.network.NetworkCallImpl
import io.github.mohamedisoliman.kalley.network.Request
import io.github.mohamedisoliman.kalley.network.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-23.
 */

private interface Downloader {

    fun load(
        url: String,
        coroutineScope: CoroutineScope,
        onSuccess: (Bitmap) -> Unit, onFailure: (Exception) -> Unit
    )

    fun cancel()
}

class ImageDownloader(
    private val dispatcher: Dispatcher,
    private val cache: Cache = Cache(20)
) : Downloader {

    override fun load(
        url: String,
        coroutineScope: CoroutineScope,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val request = Request(url)
        val response: Response? = cache.getResponse(request)
        if (response != null) {
            onSuccess(response.stream.toBitmap())
        } else {
            val networkCall = NetworkCallImpl(coroutineScope, request, {
                cache.save(request, it)
                onSuccess(it.stream.toBitmap())
            }, {
                onFailure(it)
            })
            dispatcher.enqueue(networkCall)
        }
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

fun BufferedInputStream.toBitmap(): Bitmap {
    this.mark(this.available())
    this.reset()
    return BitmapFactory.decodeStream(this)
}