package io.ktor.client.engine.js.test

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlin.js.*
import kotlin.test.*

class RequestTest {

    @Test
    fun simpleRequestTest(): Promise<Unit> = GlobalScope.async(Dispatchers.Default, CoroutineStart.DEFAULT) {
        val client = HttpClient()
        val response: HttpResponse = client.get("https://www.google.com")

        assertTrue(response.status.isSuccess())
        val text = response.readText()
        println(text)

    }.asPromise().then {
        println("done")
    }

}
