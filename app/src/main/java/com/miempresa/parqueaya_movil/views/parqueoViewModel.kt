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
    // Estado mutable para mensajes de la vista
    private val _mensaje = MutableStateFlow("")

    // Estado mutable para almacenar la lista de parqueos
    private val _listaDeParqueos = MutableStateFlow<List<Parqueo>>(emptyList())
    val listaDeParqueos: StateFlow<List<Parqueo>> = _listaDeParqueos

    // Configuración de Retrofit para manejar las solicitudes a la API
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.parqueaya.xyz/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Instancia del servicio de Parqueo utilizando Retrofit
    private val parqueoService = retrofit.create(ParqueoService::class.java)

    // Función para obtener todos los parqueos desde el servicio
    fun obtenerTodosLosParqueos() {
        viewModelScope.launch {
            _mensaje.value = "Obteniendo todos los parqueos" // Actualización del mensaje para la vista
            try {
                val response = parqueoService.obtenerParqueos() // Petición al servicio para obtener parqueos
                if (response.isNotEmpty()) {
                    _listaDeParqueos.value = response // Actualiza la lista de parqueos en el ViewModel
                    _mensaje.value = "Se obtuvieron ${response.size} parqueos" // Mensaje de éxito con la cantidad de parqueos obtenidos
                } else {
                    _mensaje.value = "No se encontraron parqueos" // Mensaje si no se encuentran parqueos
                }
            } catch (e: Exception) {
                _mensaje.value = "Error en la conexión: ${e.message}" // Mensaje en caso de error de conexión
            }
        }
    }
}
