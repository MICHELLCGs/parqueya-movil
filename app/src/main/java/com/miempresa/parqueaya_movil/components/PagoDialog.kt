package com.miempresa.parqueaya_movil.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagoDialog(
    show: Boolean,
    onClose: () -> Unit
){
    var tarifa by remember { mutableStateOf(0) }
    var numtarjeta by remember { mutableStateOf("") }
    if(show){
        Dialog(onDismissRequest = { /*TODO*/ }, properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White, RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Su total a pagar:", fontWeight = FontWeight.Bold)
                Text(text = "$tarifa")
                Text(text = "Ingrese su número de tarjeta", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(value = numtarjeta, onValueChange ={numtarjeta=it} )
                Spacer(modifier = Modifier.height(8.dp))

                Row (modifier=Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Button(onClick = onClose ) {
                        Text(text = "Cancelar")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Pagar")
                    }
                }
            }
        }
    }
}