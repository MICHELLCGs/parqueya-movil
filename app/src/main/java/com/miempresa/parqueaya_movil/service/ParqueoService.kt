package com.miempresa.parqueaya_movil.service

import com.miempresa.parqueaya_movil.model.Parqueo
import retrofit2.http.GET
import retrofit2.http.Path

interface ParqueoService {
    @GET("/v1/parqueos/parqueos/")
    suspend fun obtenerParqueos(): List<Parqueo>
    @GET("parqueos/parqueos/{id}/")
    suspend fun obtenerParqueoPorId(@Path("id") id: Int): Parqueo
}