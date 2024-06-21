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

sealed class ViewState<out T> {
    object Idle : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
}

@Composable
fun <T> HandleViewState(
    viewState: ViewState<T>,
    navController: NavController,
    successRoute: String,
    popUpToRoute: String
) {
    when (viewState) {
        is ViewState.Loading -> CircularProgressIndicator()
        is ViewState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(successRoute) {
                    popUpTo(popUpToRoute) { inclusive = true }
                }
            }
        }
        is ViewState.Error -> {
            Text(
                text = viewState.message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        else -> {}
    }
}
