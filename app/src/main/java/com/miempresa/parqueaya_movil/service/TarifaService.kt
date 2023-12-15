package com.miempresa.parqueaya_movil.service

import com.miempresa.parqueaya_movil.model.Parqueo
import com.miempresa.parqueaya_movil.model.Tarifa
import retrofit2.http.GET

interface TarifaService {
    @GET("/v1/servicios/tarifa/")
    suspend fun obtenerTarifas(): List<Tarifa>
}