package dsl

import Client

suspend fun client(init: Client.() -> Unit): Client {
    val client = Client()
    client.init(init)
    return client
}