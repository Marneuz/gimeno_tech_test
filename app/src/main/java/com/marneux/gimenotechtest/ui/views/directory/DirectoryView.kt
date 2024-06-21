package com.marneux.gimenotechtest.ui.views.directory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marneux.gimenotechtest.R
import com.marneux.gimenotechtest.ui.theme.CorpoBlue
import com.marneux.gimenotechtest.ui.views.directory.composables.EmployeeCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DirectoryView(navController: NavController, viewModel: DirectoryViewModel = hiltViewModel()) {
    val groupedEmployees by viewModel.groupedEmployees.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val listState = rememberLazyListState()
    var isSearching by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { viewModel.updateSearchQuery(it) },
                            placeholder = { Text("Buscar...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.icono),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(end = 8.dp)
                            )
                            Spacer(modifier = Modifier.size(32.dp))
                            Text("Directorio", style = MaterialTheme.typography.titleLarge)
                        }
                    }
                },
                actions = {
                    if (isSearching) {
                        IconButton(onClick = { isSearching = false }) {
                            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Cerrar " +
                                    "búsqueda", Modifier.size(34.dp), tint = CorpoBlue)
                        }
                    } else {
                        IconButton(onClick = { isSearching = true }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar",
                                Modifier.size(34.dp), tint = CorpoBlue)
                        }
                        IconButton(onClick = {
                            navController.navigate("login") {
                                popUpTo("directory") { inclusive = true }
                            }
                        }) {
                            Icon(Icons.Outlined.MoreVert, contentDescription = "Logout", Modifier
                                .size(34.dp), tint = CorpoBlue)
                        }
                    }
                },
                modifier = Modifier.border(1.dp, Color.LightGray, RectangleShape)
            )
        },
        content = { padding ->
            LazyColumn(
                state = listState,
                contentPadding = padding
            ) {
                groupedEmployees.forEach { (initial, employees) ->
                    stickyHeader {
                        Text(
                            text = initial.toString(),
                            color = Color.Gray,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.background)
                        )
                    }
                    items(employees) { employee ->
                        EmployeeCard(employee, onClick = {
                            navController.navigate("details/${employee.id}")
                        })
                    }
                }
            }
        }
    )
}


