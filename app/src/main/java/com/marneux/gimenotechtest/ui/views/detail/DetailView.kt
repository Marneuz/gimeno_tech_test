package com.marneux.gimenotechtest.ui.views.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.marneux.gimenotechtest.R
import com.marneux.gimenotechtest.ui.theme.CorpoBlue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController, viewModel: DetailViewModel = hiltViewModel(), employeeId: Int) {
    LaunchedEffect(employeeId) {
        viewModel.loadEmployeeById(employeeId)
    }

    val employee = viewModel.employee.collectAsState().value
    val context = LocalContext.current

    employee?.let {
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(it.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${it.name} ${it.lastName}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = it.position,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(color = Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${it.phoneNumber}")
                                }
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.phone),
                                contentDescription = "Teléfono",
                                modifier = Modifier.size(40.dp),
                                tint = CorpoBlue
                            )
                        }
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${it.email}")
                                }
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.mail),
                                contentDescription = "Email",
                                modifier = Modifier.size(40.dp),
                                tint = CorpoBlue
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = it.phoneNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                            .align(Alignment.Start)
                    )
                    Text(
                        text = it.email,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                            .align(Alignment.Start)
                    )
                }
            }
        )
    }
}
