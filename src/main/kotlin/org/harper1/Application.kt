package org.harper1

import io.ktor.server.application.*

fun Application.module() {
    configureFrameworks()
    configureHTTP()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
