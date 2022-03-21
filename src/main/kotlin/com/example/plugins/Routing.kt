package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

data class HelloResponse(
    val message: String
)

fun Application.configureRouting() {
    routing {
        get("/") {
            val data = HelloResponse("hello")
            call.respond(data)
        }
    }
    routing {
        get("/users/{user}") {
            this.call.respond(HelloResponse("Hello, ${call.parameters["user"]}"))
        }
    }
}
