package io.github.mohamedisoliman.kalley.network

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-24.
 */
class Dispatcher(private val maxRequests: Int = 64) {

    private val callsList = mutableListOf<NetworkCall>()

    fun enqueue(networkCall: NetworkCall) {
        if (callsList.contains(networkCall)) return //forbid executing same call
        require(callsList.size < maxRequests) { "TODO" }
        callsList.add(networkCall)
        networkCall.enqueue().invokeOnCompletion {
            callsList.remove(networkCall)
        }
    }

    private fun cancel(url: String) {
        callsList.find { it.request().url == url }?.cancel()
    }

    fun cancelAll() {
        callsList.forEach { it.cancel() }
    }

}