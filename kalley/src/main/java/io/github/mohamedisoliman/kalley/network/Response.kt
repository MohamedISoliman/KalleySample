package io.github.mohamedisoliman.kalley.network

import java.io.BufferedInputStream

/**
 *
 * Created by Mohamed Ibrahim on 2020-01-23.
 */
data class Response(val request: Request, val stream: BufferedInputStream)