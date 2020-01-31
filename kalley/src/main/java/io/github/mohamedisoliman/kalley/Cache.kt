package io.github.mohamedisoliman.kalley

import android.util.LruCache
import io.github.mohamedisoliman.kalley.network.Request
import io.github.mohamedisoliman.kalley.network.Response

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-20.
 */
class Cache(count: Int) {


    private val lruCache = LruCache<Request, Response>(count)

    fun clear() {
        lruCache.evictAll()
    }

    fun save(request: Request, response: Response) {
        lruCache.put(request, response)
    }

    fun getResponse(request: Request): Response? = lruCache[request]
}