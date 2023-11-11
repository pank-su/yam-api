package dsl

import Client

fun client(init: Client.() -> Unit): Client {
    val client = Client()
    client.init()
    return client
}