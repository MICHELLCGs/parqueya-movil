@file:OptIn(ExperimentalMaterial3Api::class)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreendos(navController: NavHostController) {

    Scaffold(
        topBar = { Toolbarprodo(navController) },
        content = { Regcontenidodo(navController) }
    )
}
@Composable
fun Toolbarprodo(navController: NavHostController) {
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
                        .clickable { navController.navigate("register") }
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Regcontenidodo(navController: NavController) {
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
            .padding(start = 10.dp, top = 80.dp)
    ) { item{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BoxWithConstraints {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.outline_account_circle_24),
                        contentDescription = "perfil",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
        Column(){
            Text(
                text = "Nombre Completo",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )
            var Nombre by remember { mutableStateOf("") }
            TextField(
                value = Nombre,
                onValueChange = {Nombre=it},
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=18.dp, bottom=18.dp)
                    .padding(start=25.dp, end=25.dp)
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
                label = {
                    Text(
                        "Ingrese su nombre completo",
                        color = Color(0x80FFFFFF)
                    )
                }
            )
            Text(
                text = "Placa de Vehículo",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )
            var placa by remember { mutableStateOf("") }
            TextField(
                value = placa,
                onValueChange = {placa=it},
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=18.dp, bottom=18.dp)
                    .padding(start=25.dp, end=25.dp)
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
                label = {
                    Text(
                        " Ingrese su placa de vehículo",
                        color = Color(0x80FFFFFF)
                    )
                }
            )
            Text(
                text = "DNI",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )
            var dni by remember { mutableStateOf("") }
            TextField(
                value = dni,
                onValueChange = {dni=it},
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=18.dp, bottom=18.dp)
                    .padding(start=25.dp, end=25.dp)
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
                label = {
                    Text(
                        "Ingrese su documento de identidad",
                        color = Color(0x80FFFFFF)
                    )
                }
            )
            Text(
                text = "Celular",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )

            var numero by remember { mutableStateOf("") }
            TextField(
                value = numero,
                onValueChange = {numero=it},
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0x5093A0BD)),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=18.dp, bottom=18.dp)
                    .padding(start=25.dp, end=25.dp)
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
                label = {
                    Text(
                        " Ingrese número de celular",
                        color = Color(0x80FFFFFF)
                    )
                }
            )
            Button(onClick = {navController.navigate("profile")},
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start=25.dp, end=25.dp)
                    .height(50.dp)
                    .background(
                        brush = gradientbut,
                        shape = RoundedCornerShape(10.dp)
                    )
            )  {
                Text(
                    text = "Confirmar",
                    color = Color(0xFFFFFFFF),
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(top = 1.dp)
                )
            }
        }
    }}
}