package mx.utng.smarthealthmonitor.model

data class HistorialLectura(
    val id: Int,
    val fecha: String,
    val fc: Int,
    val pasos: Int,
    val esNormal: Boolean
)