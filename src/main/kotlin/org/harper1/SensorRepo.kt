package org.harper1

import org.jetbrains.exposed.sql.selectAll

interface SensorRepo {
    suspend fun getAllSensors(): List<ESPSensor>
    suspend fun getSensorByMac(mac: String): ESPSensor?
}

class SensorRepoImpl : SensorRepo {
    override suspend fun getAllSensors(): List<ESPSensor> {
        return dbQuery {
            Sensors.selectAll()
                .map { it.toESPSensor() }
        }
    }

    override suspend fun getSensorByMac(mac: String): ESPSensor? {
        return dbQuery {
            Sensors.selectAll()
                .where { Sensors.mac eq mac }
                .map { it.toESPSensor() }
                .singleOrNull()
        }
    }
}
