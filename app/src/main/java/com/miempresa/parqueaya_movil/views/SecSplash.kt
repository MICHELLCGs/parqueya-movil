package com.miempresa.parqueaya_movil.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.miempresa.parqueaya_movil.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1=true){
        delay(2000)
        navController.popBackStack()
        navController.navigate("login")
    }
    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE4EAF8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = R.drawable.parqueaya), contentDescription = "logo",
            modifier=Modifier
                .height(200.dp)
                .width(200.dp))
        Text("Bienvenido",
            fontSize=30.sp,
            fontWeight= FontWeight.Bold)
    }
}
@Preview(showBackground=true)
@Composable
fun SplashScreenPreview(){
    Splash()
}