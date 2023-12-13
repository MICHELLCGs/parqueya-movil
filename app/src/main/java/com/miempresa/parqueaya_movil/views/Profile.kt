@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.miempresa.parqueaya_movil.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController , emailText: String, password: String) {
    Scaffold (
        topBar = { Toolbarmenu()},
        content = { procontenido(navController,  emailText, password) }
    )
}
@Preview
@Composable
fun Toolbarmenu(){
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon={
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(id = androidx.compose.ui.R.string.navigation_menu),
                tint = Color.White,
                modifier = Modifier
                    .size(60.dp)
                    .clickable { /* Handle navigation click */ }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF131440))
    )
}
@Composable
fun procontenido(navController: NavController,  emailText: String, password: String){
    var nombre by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf(0) }
    val db = FirebaseFirestore.getInstance()
    val auth = Firebase.auth
    val gradient= Brush.linearGradient(
        0.0f to Color(0xFF13143E),
        500.0f to Color(0xFF0E116A),
        start= Offset.Zero,
        end=Offset.Infinite
    )
    LaunchedEffect(Unit) {
        val userDoc = db.collection("usuarios").document(auth.currentUser!!.uid)
        userDoc.addSnapshotListener { snapshot, _ ->
            if (snapshot != null && snapshot.exists()) {
                nombre = snapshot.getString("nombre") ?: ""
            }
        }
    }

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
            .padding(start = 15.dp, top = 80.dp, end = 15.dp)
    ){item{
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
            Row(
                modifier=Modifier
                    .padding(20.dp)
            ) {
                Text(text = "Hola, ",
                    color = Color(0x80FFFFFF),
                    fontSize = 20.sp)
                Text(text = "$nombre",
                    color = Color.White,
                    fontSize = 20.sp)
            }
            Row(
                modifier=Modifier
                    .padding(20.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF6180EC), CircleShape)
                        .clickable(onClick = { })
                ) {
                    Image(
                        painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.notificacion),
                        contentDescription = "notificacion",
                        modifier = Modifier
                            .size(56.dp)
                            .clickable { navController.navigate("notificaciones") }
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(40.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF6180EC), CircleShape)
                        .clickable(onClick = { /* Acción al hacer clic en el icono de carro */ })
                ) {
                    Image(
                        painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.car),
                        contentDescription = "car",
                        modifier = Modifier
                            .size(56.dp)
                            .clickable { navController.navigate("parqueo") }
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(40.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF6180EC), CircleShape)
                        .clickable(onClick = { /* Acción al hacer clic en el icono QR */ })
                ) {
                    Image(
                        painter = painterResource(id = com.miempresa.parqueaya_movil.R.drawable.qr),
                        contentDescription = "qr",
                        modifier = Modifier
                            .size(56.dp)
                            .clickable { navController.navigate("tickets") }
                            .padding(8.dp)
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(180.dp)
                    .height(60.dp)
                    .border(2.dp, Color(0xFF6180EC), RectangleShape)
                    .background(Color(0x80232675))
            ){
                Text(text = "$monto",
                    textAlign = TextAlign.Center,
                    color= Color.White,
                    modifier=Modifier)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(220.dp)
                    .border(2.dp, Color(0xFF6180EC), RectangleShape)
                    .background(Color(0x1A232675))
            )
            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 25.dp, end = 25.dp,bottom=25.dp)
                    .height(50.dp)
                    .background(
                        brush = gradientbut,
                        shape = RoundedCornerShape(10.dp)
                    )
            )  {
                Text(
                    text = "Paga aquí",
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