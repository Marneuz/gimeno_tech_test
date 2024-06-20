package com.marneux.gimenotechtest.ui.views.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marneux.gimenotechtest.ui.theme.CorpoBlue
import com.marneux.gimenotechtest.ui.views.directory.DirectoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController, employeeId: Int, viewModel: DirectoryViewModel =
    hiltViewModel()) {
    val employee = viewModel.getEmployeeById(employeeId).collectAsState(initial = null).value

    Scaffold(
        topBar = {
            TopAppBar(
                {
                    IconButton(onClick = {
                        navController.navigate("directory") {
                            popUpTo("detail") { inclusive = true }
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Directory", Modifier
                                .size(34.dp), tint = CorpoBlue
                        )
                    }
                },
                modifier = Modifier.border(1.dp, Color.LightGray, RectangleShape),
            )
        },


        content = { padding ->
            employee?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    Text(text = "Nombre: ${employee.name} ${employee.lastName}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Posición: ${employee.position}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Teléfono: ${employee.phoneNumber}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Correo Electrónico: ${employee.email}")
                }
            }
        }
    )
}