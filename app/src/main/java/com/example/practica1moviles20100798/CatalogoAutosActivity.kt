package com.example.practica1moviles20100798

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment

class CatalogoAutosActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaCatalogoAutos(
                onBackClick = { finish() } // Regresar al menú principal
            )
        }
    }
}

data class Auto(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imagenUrl: String
)

// Mock Data (autos deportivos)
val autosList = listOf(
    Auto("Ferrari", "488 Spider", 320000.0, "https://cdn.ferrari.com/cms/network/media/img/resize/60b4b11b2cf99c001e38a9f3-ferrari-488-spider-range-page-header-desktop"),
    Auto("Lamborghini", "Huracán EVO", 310000.0, "https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/model/huracan/huracan-evo/rwd/2021/04_29/HEVO_RWD_SPYDER_OVERVIEW_02_M.jpg"),
    Auto("Porsche", "911 Turbo S", 230000.0, "https://files.porsche.com/filestore/image/multimedia/none/992-turbo-s-modelimage-sideshot/thumbwhite/fb5dbf90-4a10-11ea-80c9-005056bbdc38;sM;twebp/porsche-thumbwhite.webp"),
    Auto("McLaren", "720S", 299000.0, "https://www.mclaren.com/media/tu2d4uob/720s-hero.jpg"),
    Auto("Aston Martin", "DB11", 245000.0, "https://www.astonmartin.com/-/media/models/db11/db11-coupe/db11-hero.jpg")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogoAutos(onBackClick: () -> Unit) {
    val total = autosList.sumOf { it.precio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(autosList) { auto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(auto.imagenUrl),
                            contentDescription = "${auto.marca} ${auto.modelo}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${auto.marca} ${auto.modelo}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text("Precio aproximado: $${auto.precio}")
                    }
                }
            }

            // Mostrar total al final
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Costo total de todos los autos: $${String.format("%,.2f", total)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
