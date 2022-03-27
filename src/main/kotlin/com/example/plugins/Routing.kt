package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class HelloResponse(
    val message: String
)

@Serializable
data class User(
    val id: Int
) {
    companion object {
        fun create(id: Int) = User(id)
        fun createUsers() = (0..9).map { create(it) }
    }
}

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
                this.call.respond(User.createUsers())
            }
            get("/{userId}") {
                val users = User.createUsers()
                val id = call.parameters["userId"]?.toInt()
                if (id !in users.indices) this.call.respond("Not Found")
                this.call.respond(User.createUsers()[id!!])
            }
        }
    }
}
