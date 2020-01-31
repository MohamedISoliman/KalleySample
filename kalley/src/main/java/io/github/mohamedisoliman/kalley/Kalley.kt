package io.github.mohamedisoliman.kalley

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.mohamedisoliman.kalley.downloader.ImageDownloader
import io.github.mohamedisoliman.kalley.network.Dispatcher
import io.github.mohamedisoliman.kalley.network.NetworkCallImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL


/**
 *
 * Created by Mohamed Ibrahim on 2020-01-20.
 */
@SuppressLint("StaticFieldLeak")
class Kalley private constructor() {

    lateinit var context: Context
    private val dispatcher = Dispatcher()
    private val imageDownloader = ImageDownloader(dispatcher)

    companion object {
        private var instance: Kalley? = null
        fun instance(): Kalley {
            if (instance == null) {
                instance = Kalley()
            }
            return instance!!
        }
    }

    fun init(app: Application) {
        this.context = app
    }

    fun loadImage(
        url: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Exception) -> Unit,
        coroutineScope: CoroutineScope
    ) {
        imageDownloader.load(url, coroutineScope, onSuccess, onFailure)
    }

}

