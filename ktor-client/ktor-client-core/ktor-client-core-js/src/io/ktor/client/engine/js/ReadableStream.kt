package io.ktor.client.engine.js

import kotlin.js.*

internal external interface ReadableStream {
    fun getReader(): ReadableStreamReader
}

internal external interface ReadableStreamReader {
    fun cancel(): Promise<dynamic>
    fun read(): dynamic
}