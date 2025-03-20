package com.jordev.agendadecontatos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jordev.agendadecontatos.views.AtualizarContato
import com.jordev.agendadecontatos.views.ListaContatos
import com.jordev.agendadecontatos.views.SalvarContato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark( Color.BLACK ))
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "listaContatos"){
                composable(route = "listaContatos"){
                    ListaContatos(navController)
                }
                composable(route = "salvarContato"){
                    SalvarContato(navController)
                }

                composable(
                    route = "atualizarContato/{uid}",
                    arguments = listOf(navArgument("uid"){})
                ){
                    AtualizarContato(navController, it.arguments?.getString("uid").toString())
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun ChangeStatusBarTextColor(darkIcons: Boolean) {
    val window = (LocalContext.current as ComponentActivity).window
    SideEffect {
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = darkIcons
    }
}