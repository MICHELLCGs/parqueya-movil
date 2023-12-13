@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.miempresa.parqueaya_movil.R

// Pantalla de inicio de sesión
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController){
    // Estructura de la pantalla usando Scaffold

    Scaffold(
        content = { Logcontenido(navController) }
    )
}
// Contenido de la pantalla de inicio de sesión
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Logcontenido(navController: NavController){
    // Definición de pinceles de gradiente
    var password by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    val auth = Firebase.auth
    val contexto = LocalContext.current
    val gradient= Brush.linearGradient(
        0.0f to Color(0xFF141639),
        500.0f to Color(0xFF0F126A),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    val gradientbut= Brush.linearGradient(
        0.0f to Color(0xFF6180EC),
        500.0f to Color(0xFF9CB0FE),
        start= Offset.Zero,
        end= Offset.Infinite
    )
    // Estado del checkbox para recordar usuario
    var checked by remember { mutableStateOf(false) }
    // Columna principal con el contenido de la pantalla
    LazyColumn(
        modifier= Modifier
            .background(gradient)
            .fillMaxSize()
    ){item{
        Column(
            verticalArrangement = Arrangement.Top,
        ){
            // Imagen de encabezado
            Image(painter = painterResource(id = R.drawable.login), contentDescription = "login",
                modifier= Modifier
                    .width(808.dp)
                    .height(222.dp)
            )
        }
        Text(text = "ParqueaYa",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)

        ){
            Text(text = "Inicio de Sesión",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier= Modifier
                    .padding(top = 15.dp)
            )
            Row{
                Text(text = "Inicio de sesión con tu cuenta de ",
                    color = Color(0xFF93A0BD),
                    fontSize = 16.sp,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                Text(text = "ParqueaYa.",
                    color = Color(0xFF93A0BD),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp))
            }
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

                TextField(
                    value = emailText,
                    onValueChange = {emailText=it},
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
                        .padding(top = 20.dp)
                )

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
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { isChecked ->
                            checked = isChecked
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White,
                        )

                    )
                    Text(
                        text = "Recuerdame",
                        color = Color(0xFF93A0BD),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 1.dp)
                            .padding(end = 15.dp)

                    )
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        color = Color(0x99FFFFFF),
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                }
                Button(
                    onClick = {
                        if (emailText.isEmpty() || password.isEmpty()) {
                            Toast.makeText(contexto, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                        } else {
                            try {
                                auth.signInWithEmailAndPassword(emailText, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(contexto, "Iniciando…", Toast.LENGTH_SHORT).show()
                                            navController.navigate("profile/$emailText/$password")
                                        } else {
                                            Toast.makeText(contexto, "No registrado", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } catch (e: Exception) {
                                Toast.makeText(contexto, "Error al iniciar sesión: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(50.dp)
                        .background(
                            brush = gradientbut,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "Iniciar Sesión",
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

                ){
                    item{
                        Text(text = "¿No tienes cuenta?  ",
                            color = Color(0xFF93A0BD),
                            fontSize = 16.sp,
                            modifier= Modifier
                                .padding(top = 30.dp, bottom=30.dp)
                        )
                        Text(text = "Regístrate.",
                            color = Color(0xFFFFFFFF),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier= Modifier
                                .padding(top = 30.dp, bottom=30.dp)
                                .clickable {navController.navigate("register")})
                    }
                }
            }
        }
    }}
}