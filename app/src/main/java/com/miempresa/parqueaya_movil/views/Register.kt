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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Regcontenido(navController: NavHostController) {
    var passwordnuevo by remember { mutableStateOf("") }
    var mailnuevo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var mensajeRegistro by remember { mutableStateOf("") }
    var mensajeFirestore by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var mostrarContraseña by remember { mutableStateOf(false) }
    val contexto = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val registerUser = {
        val auth = Firebase.auth
        if (mailnuevo.isNotEmpty() && passwordnuevo.isNotEmpty() && nombre.isNotEmpty() && apellidos.isNotEmpty() && dni.isNotEmpty()) {
            try {
                auth.createUserWithEmailAndPassword(mailnuevo, passwordnuevo)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val db = FirebaseFirestore.getInstance()

                            val user = hashMapOf(
                                "correo" to mailnuevo,
                                "password" to passwordnuevo,
                                "nombre" to nombre,
                                "apellidos" to apellidos,
                                "dni" to dni
                            )

                            db.collection("usuarios").document(auth.currentUser!!.uid)
                                .set(user)
                                .addOnSuccessListener {
                                    mensajeFirestore = "Datos almacenados en Firestore correctamente"
                                    navController.navigate("profile/$mailnuevo/$passwordnuevo")
                                }
                                .addOnFailureListener { e ->
                                    mensajeFirestore = "Error al almacenar en Firestore: ${e.message}"
                                }
                            // Mostrar un toast cuando el registro es exitoso
                            coroutineScope.launch {
                                Toast.makeText(
                                    contexto,
                                    "Usuario creado exitosamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            // Mostrar un toast en caso de fallo en el registro
                            coroutineScope.launch {
                                Toast.makeText(
                                    contexto,
                                    "Fallo al registrarse: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
            } catch (e: Exception) {
                // Mostrar un toast si ocurre un error durante el registro
                coroutineScope.launch {
                    Toast.makeText(
                        contexto,
                        "Error al registrar al usuario: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            // Mostrar un toast si algún campo está vacío
            coroutineScope.launch {
                Toast.makeText(
                    contexto,
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


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

                TextField(
                    value = mailnuevo,
                    onValueChange = {mailnuevo=it},
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
                    value = passwordnuevo,
                    onValueChange = {passwordnuevo=it},
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
                    visualTransformation = if (mostrarContraseña) VisualTransformation.None else PasswordVisualTransformation()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Checkbox(
                        checked = mostrarContraseña,
                        onCheckedChange = { isChecked -> mostrarContraseña = isChecked },
                        colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
                    )
                    Text(
                        text = "Mostrar",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Text(text = "Nombre",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = nombre,
                    onValueChange = {nombre=it},
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
                    label = { Text("Nombre",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Text(text = "Apellido",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = apellidos,
                    onValueChange = {apellidos=it},
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
                    label = { Text("Apellido",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Text(text = "DNI",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
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
                    label = { Text("DNI",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Button(onClick = {
                    registerUser()
                },
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
                Text(
                    text = "$mensajeRegistro\n$mensajeFirestore",
                    modifier = Modifier.padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )

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
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = "Inicia Sesión.",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 20.dp)
                                .clickable {navController.navigate("login")}
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp)
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