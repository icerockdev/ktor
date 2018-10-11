package io.ktor.client.engine.js

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.io.*
import org.khronos.webgl.*
import kotlin.coroutines.*
import kotlin.js.*

internal external interface ReadableStream {
    fun getReader(): ReadableStreamReader
}

internal external interface ReadResult {
    val done: Boolean
    val value: Uint8Array?
}

internal external interface ReadableStreamReader {
    fun cancel(): Promise<dynamic>
    fun read(): Promise<ReadResult>
}

internal fun ReadableStream.toByteChannel(
    callContext: CoroutineContext
): ByteReadChannel = GlobalScope.writer(callContext) {
    val reader = getReader()
    while (true) {
        val chunk = reader.readChunk() ?: break
        channel.writeFully(chunk.toByteArray())
    }
}.channel

internal suspend fun ReadableStreamReader.readChunk(): Uint8Array? = suspendCancellableCoroutine { continuation ->
    read().then {
        val chunk = it.value
        val result = Result.success(if (it.done || chunk == null) null else chunk)
        continuation.resumeWith(result)
    }
}

internal fun Uint8Array.toByteArray(): ByteArray = ByteArray(length) { get(it) }
