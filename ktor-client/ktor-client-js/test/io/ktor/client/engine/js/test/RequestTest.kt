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
    fun simpleRequestTest(): Promise<Unit> = GlobalScope.promise {
        val client = HttpClient()
        val text = client.get<String>("https://www.ya.ru")
    }.catch {  }

}
