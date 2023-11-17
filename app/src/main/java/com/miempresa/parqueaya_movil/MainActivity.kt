package com.miempresa.parqueaya_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.miempresa.parqueaya_movil.navegacion.nav
import com.miempresa.parqueaya_movil.ui.theme.Parqueaya_movilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Establecer el contenido de la actividad con el Composable "nav()"
            nav()
        }
    }
}


// Vista previa de la aplicación
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Vista previa del Composable "nav()"
    nav()
}