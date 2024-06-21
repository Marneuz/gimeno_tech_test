package com.marneux.gimenotechtest.ui.views.register

import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marneux.gimenotechtest.R
import com.marneux.gimenotechtest.domain.usecases.isValidEmail
import com.marneux.gimenotechtest.domain.usecases.isValidPassword
import com.marneux.gimenotechtest.ui.views.common.BottomLogo
import com.marneux.gimenotechtest.ui.views.common.CustomTextField
import com.marneux.gimenotechtest.ui.views.common.HandleViewState
import com.marneux.gimenotechtest.ui.views.common.MainLogo

@Composable
fun RegisterView(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isRegisterEnabled =
        email.isValidEmail() && password.isValidPassword() && password == confirmPassword

    val registerState by viewModel.registerState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
 MainLogo()
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.user),
            icon = Icons.Outlined.Person
        )
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            icon = Icons.Outlined.Lock,
            visualTransformation = PasswordVisualTransformation()
        )
        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = stringResource(R.string.confirm_password),
            icon = Icons.Outlined.Lock,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.weight(0.7f))
        Button(
            onClick = { viewModel.register(email, password) },
            enabled = isRegisterEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.create_account))
        }

        HandleViewState(
            viewState = registerState,
            navController = navController,
            successRoute = "login",
            popUpToRoute = "login"
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomLogo()
    }
}