package org.harper1

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting(repo: SensorRepo = get()) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/sensors/{mac}") {
            val mac = call.parameters["mac"] ?: throw IllegalArgumentException("Invalid mac address")
            repo.getSensorByMac(mac)?.let {
                call.respond(HttpStatusCode.OK, it)
            } ?: run { call.respond(HttpStatusCode.NotFound) }
        }

        get("/sensors/list") {
            val sensors = repo.getAllSensors()
            call.respond(HttpStatusCode.OK, sensors)
        }
    }
}
