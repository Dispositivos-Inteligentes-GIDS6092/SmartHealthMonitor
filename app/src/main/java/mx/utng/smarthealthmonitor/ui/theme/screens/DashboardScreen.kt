package mx.utng.smarthealthmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.utng.smarthealthmonitor.data.mockHistorial
import mx.utng.smarthealthmonitor.ui.components.FilaHistorial
import mx.utng.smarthealthmonitor.ui.components.TarjetaDato
import mx.utng.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onHistorialClick: () -> Unit = {},
    onAlertClick: () -> Unit = {}
) {
    val pasos = 4250
    val fcActual = 78
    val historial = mockHistorial

    val esNormal = fcActual in 60..100
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
            item {
                TarjetaDato(
                    valor = fcActual.toString(),
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
            item {
                TarjetaDato(
                    valor = "%,d".format(pasos),
                    unidad = "pasos",
                    label = "Pasos del día",
                    colorValor = MaterialTheme.colorScheme.primary,
                    icono = Icons.Default.DirectionsBike
                )
            }
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
            items(
                items = historial,
                key = { it.id }
            ) { lectura ->
                FilaHistorial(lectura = lectura)
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