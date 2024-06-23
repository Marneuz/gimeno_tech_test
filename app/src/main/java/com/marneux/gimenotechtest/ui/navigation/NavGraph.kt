package com.marneux.gimenotechtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marneux.gimenotechtest.ui.views.detail.DetailView
import com.marneux.gimenotechtest.ui.views.directory.DirectoryView
import com.marneux.gimenotechtest.ui.views.login.LoginView
import com.marneux.gimenotechtest.ui.views.register.RegisterView
import com.marneux.gimenotechtest.ui.views.splash.SplashScreen

@Composable
fun NavGraph(startDestination: String = "splash") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("splash") { SplashScreen(navController) } // Pantalla de inicio
        composable("login") { LoginView(navController) } // Pantalla de inicio de sesión
        composable("register") { RegisterView(navController) } // Pantalla de registro
        composable("directory") { DirectoryView(navController) } // Pantalla de directorio
        composable("details/{employeeId}") { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString("employeeId")?.toInt() ?: 0
            DetailView(navController = navController, employeeId = employeeId) // Pantalla de detalles del empleado
        }
    }
}
