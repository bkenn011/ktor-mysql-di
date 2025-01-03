package org.harper1

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = environment.config.property("mysql.jdbcUrl").getString(),
        driver = "com.mysql.cj.jdbc.Driver",
        user = environment.config.property("mysql.dbUser").getString(),
        password = environment.config.property("mysql.dbPw").getString()
    ).apply {
        transaction(this) {
            SchemaUtils.create(Sensors)
        }
    }
}

suspend fun <T> dbQuery(logger: SqlLogger? = null, block: suspend () -> T): T {
    return newSuspendedTransaction(Dispatchers.IO) {
        logger?.let { addLogger(it) }
        block()
    }
}
