package mx.utng.smarthealthmonitor.data

import java.util.UUID

data class LecturaFC(
    val id: String = UUID.randomUUID().toString(),
    val valorBpm: Int,
    val hora: String,
    val esNormal: Boolean
)

val mockHistorial = listOf(
    LecturaFC(valorBpm = 95, hora = "08:30", esNormal = false),
    LecturaFC(valorBpm = 72, hora = "10:15", esNormal = true),
    LecturaFC(valorBpm = 68, hora = "12:00", esNormal = true),
    LecturaFC(valorBpm = 102, hora = "14:45", esNormal = false),
    LecturaFC(valorBpm = 88, hora = "16:20", esNormal = true),
    LecturaFC(valorBpm = 65, hora = "18:10", esNormal = true),
    LecturaFC(valorBpm = 110, hora = "20:00", esNormal = false)
)