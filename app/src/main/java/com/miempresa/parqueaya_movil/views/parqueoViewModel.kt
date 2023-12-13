package com.miempresa.parqueaya_movil.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miempresa.parqueaya_movil.model.Parqueo
import com.miempresa.parqueaya_movil.service.ParqueoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParqueoViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("")

    private val _listaDeParqueos = MutableStateFlow<List<Parqueo>>(emptyList()) // Cambio aquí
    val listaDeParqueos: StateFlow<List<Parqueo>> = _listaDeParqueos // Cambio aquí

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.parqueaya.xyz/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val parqueoService = retrofit.create(ParqueoService::class.java)

    // Función para obtener todos los parqueos
    fun obtenerTodosLosParqueos() {
        viewModelScope.launch {
            _mensaje.value = "Obteniendo todos los parqueos"
            try {
                val response = parqueoService.obtenerParqueos()
                if (response.isNotEmpty()) {
                    _listaDeParqueos.value = response // Actualiza la lista de parqueos en el ViewModel
                    _mensaje.value = "Se obtuvieron ${response.size} parqueos"
                } else {
                    _mensaje.value = "No se encontraron parqueos"
                }
            } catch (e: Exception) {
                _mensaje.value = "Error en la conexión: ${e.message}"
            }
        }
    }
}