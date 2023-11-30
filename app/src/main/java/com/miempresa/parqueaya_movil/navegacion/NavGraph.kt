package com.miempresa.parqueaya_movil.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miempresa.parqueaya_movil.views.AutosScreen
import com.miempresa.parqueaya_movil.views.LoginScreen
import com.miempresa.parqueaya_movil.views.NotiScreen
import com.miempresa.parqueaya_movil.views.ProfileScreen
import com.miempresa.parqueaya_movil.views.RegisterScreen
import com.miempresa.parqueaya_movil.views.RegisterScreendos
import com.miempresa.parqueaya_movil.views.Splash
import com.miempresa.parqueaya_movil.views.SplashScreen
import com.miempresa.parqueaya_movil.views.TicketsScreen

@Composable
fun nav(){
    // Se crea un controlador de navegación para manejar las transiciones entre destinos
    val navController= rememberNavController()

    // Componente NavHost que define la estructura de navegación
    NavHost(navController = navController, startDestination = "secsplash" ){
        // Definición del destino "login" que muestra la pantalla de inicio de sesión
        composable(route="login"){
            LoginScreen(navController)
        }

        // Definición del destino "register" que muestra la pantalla de registro
        composable(route="register"){
            RegisterScreen(navController)
        }

        // Definición del destino "registerdos" que muestra otra pantalla de registro (posiblemente diferente a la anterior)
        composable(route="registerdos"){
            RegisterScreendos(navController)
        }

        // Definición del destino "profile" que muestra la pantalla de perfil del usuario
        composable(route="profile"){
            ProfileScreen(navController)
        }

        composable(route="autos"){
            AutosScreen(navController)
        }
        composable(route="notificaciones"){
            NotiScreen(navController)
        }
        composable(route="tickets"){
            TicketsScreen(navController)
        }
        composable(route="secsplash"){
            SplashScreen(navController)
        }
    }
}
