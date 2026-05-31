package mx.utng.smarthealthmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.utng.smarthealthmonitor.data.LecturaFC  // ← Usamos LecturaFC
import mx.utng.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.smarthealthmonitor.data.mockHistorial  // ← Importamos mockHistorial
import mx.utng.smarthealthmonitor.ui.components.FilaHistorial
import mx.utng.smarthealthmonitor.ui.components.TarjetaDato
import mx.utng.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme
import mx.utng.smarthealthmonitor.ui.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onHistorialClick: () -> Unit = {},
    onAlertClick: () -> Unit = {},
    viewModel: DashboardViewModel = viewModel()
) {
    // Reactividad: collectAsState() convierte StateFlow en State de Compose
    val fc by viewModel.fc.collectAsState()
    val pasos by viewModel.pasos.collectAsState()

    // Usamos mockHistorial directamente (LecturaFC)
    val historial = mockHistorial

    val esNormal = fc in 60..100
    val chipTexto = if (esNormal) "Normal" else "Consulta al médico"
    val chipColor = if (esNormal) Green else Red

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAlertClick,
                containerColor = MaterialTheme.colorScheme.error
            ) {
                Icon(Icons.Default.AddAlert, contentDescription = "Alerta")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Smart Health Monitor") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tarjeta de Frecuencia Cardiaca
            item {
                TarjetaDato(
                    valor = fc.toString(),
                    unidad = "bpm",
                    label = "Frecuencia cardiaca",
                    colorValor = if (esNormal) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    icono = Icons.Default.Favorite
                )
                if (!esNormal) {
                    Spacer(Modifier.height(8.dp))
                    AssistChip(
                        onClick = { },
                        label = { Text(chipTexto) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = chipColor.copy(alpha = 0.1f),
                            labelColor = chipColor
                        )
                    )
                }
            }

            // Tarjeta de Pasos
            item {
                TarjetaDato(
                    valor = "%,d".format(pasos),
                    unidad = "pasos",
                    label = "Pasos del día",
                    colorValor = MaterialTheme.colorScheme.primary,
                    icono = Icons.Default.DirectionsBike
                )
            }

            // Historial reciente
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Historial reciente", style = MaterialTheme.typography.titleMedium)
                    TextButton(onClick = onHistorialClick) {
                        Text("Ver todo")
                    }
                }
            }

            // Usamos LecturaFC para el historial
            items(
                items = historial,
                key = { it.id }
            ) { lectura ->
                FilaHistorial(lectura = lectura)
            }

            // 🔥🔥🔥 BOTÓN DE SIMULACIÓN 🔥🔥🔥
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🧪 MODO PRUEBAS",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                // Simular lectura del wearable con valores aleatorios
                                val fcSimulado = (60..120).random()
                                val pasosSimulados = (3000..10000).random()

                                SmartHealthRepository.actualizarFC(fcSimulado)
                                SmartHealthRepository.actualizarPasos(pasosSimulados)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("📱 Simular dato del wearable (PRUEBAS)")
                        }
                        Text(
                            text = "Presiona para generar valores aleatorios de FC y Pasos",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Dashboard - Light", showSystemUi = true)
@Preview(showBackground = true, name = "Dashboard - Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DashboardScreenPreview() {
    SmartHealthMonitorTheme {
        DashboardScreen()
    }
}