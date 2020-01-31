package io.github.mohamedisoliman.kalley.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.net.URL

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-24.
 */
class NetworkCallImpl(
    private val coroutineScope: CoroutineScope,
    private val request: Request,
    private val onSuccess: (Response) -> Unit,
    private val onFailure: (Exception) -> Unit
) : NetworkCall {

    var job: Job? = null

    override fun request(): Request = request

    override fun enqueue(): Job {
        job = coroutineScope.launch {
            try {
                val response = async(Dispatchers.IO) {
                    val connection = URL(request.url).openConnection()
                    val inputStream = BufferedInputStream(connection.getInputStream())
                    Response(request, inputStream)
                }.await()
                withContext(Dispatchers.Main) {
                    onSuccess(response)
                }
            } catch (e: Exception) {
                onFailure(e)
            }
        }
        return job!!
    }

    override fun cancel() {
        job?.cancel()
    }

}