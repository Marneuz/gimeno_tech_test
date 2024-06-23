package com.marneux.gimenotechtest.ui.views.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marneux.gimenotechtest.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    // Observa el estado de `hasUser` del ViewModel
    val hasUser by viewModel.hasUser.collectAsState(initial = false)

    // Efecto lanzado cuando se carga
    LaunchedEffect(Unit) {
        delay(1000) // Simula el tiempo de carga con un retraso de 1 segundo
        if (hasUser) {
            // Navega al directorio si existe un usuario registrado
            navController.navigate("directory") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // Navega a la pantalla de inicio de sesión si no existe un usuario registrado
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // Muestra una caja centrada con un mensaje de carga
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.loading), style = MaterialTheme.typography.titleLarge)
    }
}
