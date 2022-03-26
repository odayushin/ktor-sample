package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

data class HelloResponse(
    val message: String
)

data class User(
    val id: Int
)

fun Application.configureRouting() {
    routing {
        get("/") {
            val data = HelloResponse("hello")
            call.respond(data)
        }
    }
    routing {
        route("/users") {
            get {
                this.call.respond(listOf(User(1), User(2)))
            }
            get("/{user}") {
                this.call.respond(HelloResponse("Hello, ${call.parameters["user"]}"))
            }
        }
    }
}
