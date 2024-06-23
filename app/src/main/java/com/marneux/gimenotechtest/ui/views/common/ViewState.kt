package com.marneux.gimenotechtest.ui.views.common

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.navigation.NavController

// Clase sellada que representa los posibles estados de la vista
sealed class ViewState<out T> {
    object Idle : ViewState<Nothing>() // Estado inicial
    object Loading : ViewState<Nothing>() // Estado de carga
    data class Success<out T>(val data: T) : ViewState<T>() // Estado de éxito con datos
    data class Error(val message: String) : ViewState<Nothing>() // Estado de error con mensaje
}

@Composable
fun <T> HandleViewState(
    viewState: ViewState<T>,
    navController: NavController,
    successRoute: String,
    popUpToRoute: String
) {
    // Maneja los diferentes estados de la vista
    when (viewState) {
        // Muestra un indicador de carga cuando el estado es Loading
        is ViewState.Loading -> CircularProgressIndicator()
        // Navega a la ruta de éxito cuando el estado es Success
        is ViewState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(successRoute) {
                    popUpTo(popUpToRoute) { inclusive = true }
                }
            }
        }
        // Muestra el mensaje de error cuando el estado es Error
        is ViewState.Error -> {
            Text(
                text = viewState.message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        // No hace nada en el estado Idle
        else -> {}
    }
}
