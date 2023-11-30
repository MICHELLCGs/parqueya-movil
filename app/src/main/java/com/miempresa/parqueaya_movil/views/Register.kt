@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavHostController) {

    Scaffold(

        topBar = { Toolbarpro(navController) },
        content = { Regcontenido(navController) }
    )
}

@Composable
fun Toolbarpro(navController: NavHostController) {
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
                        .clickable { navController.navigate("login") }
                )
                Text(
                    text = "ParqueaYa",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF131440))
    )
}

@Composable
fun Regcontenido(navController: NavHostController) {
    val gradient= Brush.linearGradient(
        0.0f to Color(0xFF13143E),
        500.0f to Color(0xFF0E116A),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    val gradientbut= Brush.linearGradient(
        0.0f to Color(0xFF6180EC),
        500.0f to Color(0xFF9BAFFD),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    LazyColumn(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
            .padding(start = 30.dp, top = 80.dp)
    ){
        item {
            Text(
                text = "Registro",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 25.dp)
            )
            Text(
                text = "Llena los siguientes datos",
                color = Color(0xFF93A0BD),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(end = 30.dp)
            ){
                Text(text = "Email",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                var email by remember { mutableStateOf("") }
                TextField(
                    value = email,
                    onValueChange = {email=it},
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 30.dp)
                        .height(56.dp)
                        .background(
                            color = Color(0x4F607FEC),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF283491),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(4.dp),
                    label = { Text("correo@gmail.com",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Text(text = "Contraseña",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 30.dp)
                )
                var password by remember { mutableStateOf("") }
                TextField(
                    value = password,
                    onValueChange = {password=it},
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(56.dp)
                        .background(
                            color = Color(0x4F607FEC), // Color de fondo con transparencia
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF283491),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(4.dp),
                    label = { Text("Contraseña",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Button(onClick = {navController.navigate("registerdos")},
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp)
                        .height(50.dp)
                        .background(
                            brush = gradientbut,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "Crear Cuenta",
                        color = Color(0xFFFFFFFF),
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                }
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    item {
                        Text(
                            text = "¿Ya tienes cuenta?  ",
                            color = Color(0xFF93A0BD),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 30.dp)
                        )
                        Text(
                            text = "Inicia Sesión.",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 30.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp)
                ) {
                    Text(
                        text = "Inicia Sesión con:",
                        color = Color(0xFF93A0BD),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Row{
                        Image(painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.facebook), contentDescription = "apple",
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .padding(end = 20.dp)
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Image(painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.google), contentDescription = "apple",
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .padding(end = 20.dp)
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Image(painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.apple), contentDescription = "apple",
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .height(30.dp)
                                .width(30.dp)
                        )
                    }
                }
            }

        }
    }
}