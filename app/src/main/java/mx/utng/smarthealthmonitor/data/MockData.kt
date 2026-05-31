package mx.utng.smarthealthmonitor.data

import mx.utng.smarthealthmonitor.model.HistorialLectura
import java.util.UUID

// Modelo original que ya tenías (lo mantenemos para compatibilidad)
data class LecturaFC(
    val id: String = UUID.randomUUID().toString(),
    val valorBpm: Int,
    val hora: String,
    val esNormal: Boolean
)

// Datos simulados para el DashboardViewModel
object MockData {
    // Valores actuales (para el dashboard)
    val fcActual = 78
    val pasosActual = 4250

    // Historial para el ViewModel (formato que espera DashboardViewModel)
    val historialFC = listOf(
        HistorialLectura(id = 1, fecha = "08:30", fc = 95, pasos = 1200, esNormal = false),
        HistorialLectura(id = 2, fecha = "10:15", fc = 72, pasos = 2300, esNormal = true),
        HistorialLectura(id = 3, fecha = "12:00", fc = 68, pasos = 3100, esNormal = true),
        HistorialLectura(id = 4, fecha = "14:45", fc = 102, pasos = 3800, esNormal = false),
        HistorialLectura(id = 5, fecha = "16:20", fc = 88, pasos = 4100, esNormal = true),
        HistorialLectura(id = 6, fecha = "18:10", fc = 65, pasos = 4250, esNormal = true),
        HistorialLectura(id = 7, fecha = "20:00", fc = 110, pasos = 4250, esNormal = false)
    )
}

// Tu mockHistorial original (lo mantenemos por si lo usas en otras partes)
val mockHistorial = listOf(
    LecturaFC(valorBpm = 95, hora = "08:30", esNormal = false),
    LecturaFC(valorBpm = 72, hora = "10:15", esNormal = true),
    LecturaFC(valorBpm = 68, hora = "12:00", esNormal = true),
    LecturaFC(valorBpm = 102, hora = "14:45", esNormal = false),
    LecturaFC(valorBpm = 88, hora = "16:20", esNormal = true),
    LecturaFC(valorBpm = 65, hora = "18:10", esNormal = true),
    LecturaFC(valorBpm = 110, hora = "20:00", esNormal = false)
)