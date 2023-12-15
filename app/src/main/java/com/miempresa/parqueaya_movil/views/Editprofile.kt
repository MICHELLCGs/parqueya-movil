@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.miempresa.parqueaya_movil.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(navController: NavController){
    Scaffold(
        topBar = { ToolbarEdit(navController) },
        content = {Editcontenido(navController)}
    )
}
@Composable
fun ToolbarEdit(navController: NavController) {
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
fun Editcontenido (navController: NavController){
    val auth = Firebase.auth
    val userCollection = FirebaseFirestore.getInstance().collection("usuarios")
    val currentUserUid = auth.currentUser?.uid
    val coroutineScope = rememberCoroutineScope()
    val contexto = LocalContext.current
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
    var contranuevo by remember { mutableStateOf("") }
    var correonuevo by remember { mutableStateOf("") }
    var nombrenuevo by remember { mutableStateOf("") }
    var apellidosnuevo by remember { mutableStateOf("") }
    var dninuevo by remember { mutableStateOf("") }
    LaunchedEffect(currentUserUid) {
        if (currentUserUid != null) {
            // Obtener los datos del usuario desde Firestore
            userCollection.document(currentUserUid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val userData = document.data
                        // Asignar los valores obtenidos a los campos editables
                        nombrenuevo = userData?.get("nombre") as? String ?: ""
                        apellidosnuevo = userData?.get("apellidos") as? String ?: ""
                        correonuevo = userData?.get("correo") as? String ?: ""
                        dninuevo = userData?.get("dni") as? String ?: ""
                        // Evitar mostrar la contraseña directamente desde Firestore por razones de seguridad
                        // Asignar un valor predeterminado o dejar el campo en blanco
                        contranuevo = "**********" // O puedes asignar una cadena vacía
                    } else {
                        // Manejar el caso en el que el documento no exista o no se haya podido recuperar
                    }
                }
                .addOnFailureListener { exception ->
                    // Manejar cualquier error al obtener los datos del usuario
                    coroutineScope.launch {
                        Toast.makeText(
                            contexto,
                            "Error al obtener datos del usuario: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnCanceledListener {
                    // Manejar la cancelación de la operación de obtener datos del usuario
                    coroutineScope.launch {
                        Toast.makeText(
                            contexto,
                            "Operación cancelada al obtener datos del usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    val Actualizar = {
        if (currentUserUid != null) {
            userCollection.document(currentUserUid)
                .update(
                    mapOf(
                        "nombre" to nombrenuevo,
                        "apellidos" to apellidosnuevo,
                        "correo" to correonuevo,
                        "dni" to dninuevo
                    )
                )
                .addOnSuccessListener {
                    coroutineScope.launch {
                        Toast.makeText(
                            contexto,
                            "Datos actualizados correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { exception ->
                    coroutineScope.launch {
                        Toast.makeText(
                            contexto,
                            "Error al actualizar datos: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            coroutineScope.launch {
                Toast.makeText(
                    contexto,
                    "El usuario actual no está autenticado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    LazyColumn(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
            .padding(start = 30.dp, top = 80.dp)
    ){
        item{
            Text(
                text = "Configuración",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 15.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(end = 30.dp)
            ){
                Text(text = "Nombre",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = nombrenuevo,
                    onValueChange = {nombrenuevo=it},
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
                    label = { Text("Nombre",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Text(text = "Apellidos",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = apellidosnuevo,
                    onValueChange = {apellidosnuevo=it},
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
                    label = { Text("apellidos",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Text(text = "Email",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = correonuevo,
                    onValueChange = {correonuevo=it},
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
                Text(text = "Dni",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier= Modifier
                        .padding(top = 20.dp)
                )
                TextField(
                    value = dninuevo,
                    onValueChange = {dninuevo=it},
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
                    label = { Text("Dni",
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
                    value = contranuevo,
                    onValueChange = {contranuevo=it},
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
                    label = { Text("Contraseña",
                        color = Color(0x80FFFFFF)
                    )
                    }
                )
                Button(onClick = {
                    Actualizar()
                },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, bottom=20.dp)
                        .height(50.dp)
                        .background(
                            brush = gradientbut,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "Actualizar",
                        color = Color(0xFFFFFFFF),
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                }
            }

        }

    }
}