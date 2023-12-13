package com.miempresa.parqueaya_movil.model

data class Parqueo(
    val capacidad: Int,
    val id: Int,
    val nombre: String,
    val tarifa_hora: String,
    val ubicacion: String
)