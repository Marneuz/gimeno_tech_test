package com.marneux.gimenotechtest.ui.views.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marneux.gimenotechtest.R
import com.marneux.gimenotechtest.ui.views.common.BottomLogo
import com.marneux.gimenotechtest.ui.views.common.CustomTextField
import com.marneux.gimenotechtest.ui.views.common.HandleViewState
import com.marneux.gimenotechtest.ui.views.common.MainLogo

@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoginEnabled = email.isNotBlank() && password.isNotBlank()

    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Aplicar el fondo según el tema
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        // Logo principal
        MainLogo()
        Spacer(modifier = Modifier.height(30.dp))

        // Campo de texto para el email
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.user),
            icon = Icons.Outlined.Person
        )

        // Campo de texto para la contraseña
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            icon = Icons.Outlined.Lock,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.weight(0.7f))

        // Botón para iniciar sesión
        Button(
            onClick = { viewModel.login(email, password) },
            enabled = isLoginEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.access))
        }

        // Botón para navegar a la vista de registro
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0077C0))
        ) {
            Text(stringResource(R.string.register), color = Color.White)
        }

        // Maneja los diferentes estados de la vista
        HandleViewState(
            viewState = loginState,
            navController = navController,
            successRoute = "directory",
            popUpToRoute = "login"
        )
        Spacer(modifier = Modifier.weight(1f))

        // Logo inferior
        BottomLogo()
    }
}
