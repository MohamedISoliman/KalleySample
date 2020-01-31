package io.github.mohamedisoliman.kalley.network

import kotlinx.coroutines.Job
import java.lang.Exception

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-24.
 */
interface NetworkCall {

    fun request(): Request

    fun enqueue(): Job

    fun cancel()
}

