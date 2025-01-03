package org.harper1

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

@Serializable
data class ESPSensor(
    val mac: String,
    val ip: String,
    val fw: String,
    val name: String?,
    @Serializable(with = DateSerializer::class) val updated: DateTime? = null
)

object Sensors : Table() {
    val mac = varchar("mac", length = 17)
    val ip = varchar("ip", 11)
    val fw = varchar("fw", 12)
    val name = varchar("name", 20).nullable()
    val updated = datetime("updated").clientDefault { DateTime() }.databaseGenerated()

    override val primaryKey = PrimaryKey(mac)
}

fun ResultRow.toESPSensor(): ESPSensor = ESPSensor(
    this[Sensors.mac],
    this[Sensors.ip],
    this[Sensors.fw],
    this[Sensors.name],
    this[Sensors.updated]
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = DateTime::class)
object DateSerializer {
    private val format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    override fun deserialize(decoder: Decoder): DateTime {
        val dateString = decoder.decodeString()
        return DateTime(format.parseDateTime(dateString))
    }

    override fun serialize(encoder: Encoder, value: DateTime) {
        val dateString = format.print(value)
        encoder.encodeString(dateString)
    }
}
