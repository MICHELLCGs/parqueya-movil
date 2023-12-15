@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.miempresa.parqueaya_movil.components.PagoDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ParqueosScreen(navController: NavController){
    val viewModel: ParqueoViewModel = viewModel()
    Scaffold(
        topBar = { ToolbarParqueo(navController) },
        content = {Parqueocontenido(navController,  viewModel)}
    )

}
@Composable
fun ToolbarParqueo(navController: NavController) {

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
fun Parqueocontenido(navController: NavController, viewModel: ParqueoViewModel){
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF13143E),
        500.0f to Color(0xFF0E116A),
        start = Offset.Zero,
        end = Offset.Infinite
    )
    val gradientcaja= Brush.linearGradient(
        0.0f to Color(0xFF6180EC),
        500.0f to Color(0xFF9BAFFD),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    val gradientpar= Brush.linearGradient(
        0.0f to Color(0xFF6180EC),
        500.0f to Color(0xFFD3DAFA),
        start= Offset.Zero,
        end= Offset.Infinite
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(top = 65.dp)
    ) {item{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center, // Alineación del contenido en la parte inferior central
                modifier = Modifier
                    .size(180.dp) // Aumenta el tamaño del círculo
                    .clip(CircleShape)
                    .padding(
                        top = 20.dp,
                        bottom = 30.dp,
                        start = 30.dp,
                        end = 24.dp
                    ) // Ajusta el espacio superior para mover el círculo hacia abajo
                    .border(5.dp, Color(0xFFFFFFFF), CircleShape)
                    .clickable(onClick = { /* Acción al hacer clic en el icono de carro */ })
            ) {
                Image(
                    painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.baseline_car),
                    contentDescription = "parqueo",
                    modifier = Modifier
                        .size(112.dp)
                        .padding(12.dp)
                        .height(40.dp)
                )
            }
            Text(text = "Parqueos", color = Color(0xFFFFFFFF),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top=150.dp, start=40.dp))
            Box(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 220.dp)
                    .size(90.dp)
                    .background(gradientcaja)
                    .border(1.dp, Color(0xFF6E89EF))
            ){
                Row(){
                    Text(text="Lista", color=Color(0xFF15196C), fontSize = 15.sp, fontWeight = FontWeight.Bold,
                        modifier=Modifier
                            .padding(start=10.dp, top=4.dp))
                    Text(text="Mapa", color=Color(0xFF15196C), fontSize = 15.sp,fontWeight = FontWeight.Bold,
                        modifier= Modifier
                            .padding(start = 110.dp, top = 4.dp)
                            .clickable { navController.navigate("mapa") })
                }
            }
        }

//        Spacer(modifier = Modifier.height(1.dp)
//            .background(gradientcaja))
        LaunchedEffect(key1 = true) {
            viewModel.obtenerTodosLosParqueos()
        }
        val listaDeParqueos by viewModel.listaDeParqueos.collectAsState()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.White)
        ) {
            items(listaDeParqueos) { parqueo ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(brush = gradientpar)
                        .clickable { /* Acción al hacer clic en un parqueo */ }
                        .border(1.dp, Color.Transparent),
                ) {
                    Text(
                        text = "ID: ${parqueo.id} - Nombre: ${parqueo.nombre} - Ubicación: ${parqueo.ubicacion}",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(12.dp)

                    )
                }
            }

        }
    }
}}