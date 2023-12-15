@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapaScreen(navController: NavController) {

    Scaffold(
        topBar = { ToolbarMapa(navController) },
        content = {Mapacontenido(navController)}
    )
}
@Composable
fun ToolbarMapa(navController: NavController) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.navigation_menu),
                    tint = Color.White,

                    modifier = Modifier
                        .size(60.dp)
                        .clickable { navController.popBackStack() }
                )
                Text(
                    text = "Atrás",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF131440))
    )
}
@Composable
fun Mapacontenido(navController: NavController) {
    val location = LatLng(-16.41734,  -71.51428)
    val camLocation = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-16.42769135, -71.519663), 10f)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(top=65.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(640.dp) // Altura del mapa
            ) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = camLocation
                ){ Marker(
                    state= MarkerState(position=location),
                    title="Parqueo Mall Aventura Arequipa",
                    snippet="Av. Porongoche 500, Arequipa 04001"
                )}
            }
        }
    }
}