package com.jordev.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jordev.agendadecontatos.AppDatabase
import com.jordev.agendadecontatos.ChangeStatusBarTextColor
import com.jordev.agendadecontatos.dao.ContatoDao
import com.jordev.agendadecontatos.itemlista.ContatoItem
import com.jordev.agendadecontatos.model.Contato
import com.jordev.agendadecontatos.ui.theme.PURPLE500
import com.jordev.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaContatos(navController: NavController) {
    val context = LocalContext.current
    val listaContatos: MutableList<Contato> = mutableListOf()
    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO){
        contatoDao = AppDatabase.getInstance(context).contatoDao()
        val contatos = contatoDao.getContatos()

        for (contato in contatos){
            listaContatos.add(contato)
        }
    }


    ChangeStatusBarTextColor(darkIcons = false) // Ícones e texto escuros
    Scaffold(
        containerColor = WHITE,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE500,
                    titleContentColor = WHITE
                ),
                title = {
                    Text(text = "Lista de Contatos")
                },
                expandedHeight = 40.dp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarContato")
                },
                containerColor = PURPLE500,
                contentColor = WHITE
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) { paddingvalue ->
        LazyColumn(modifier = Modifier.padding(paddingvalue).padding(0.dp, 20.dp, 0.dp, 50.dp)){
            itemsIndexed(listaContatos){position, item ->
                ContatoItem(
                    navController = navController,
                    position = position,
                    listaContatos = listaContatos,
                    context = context
                )
            }
        }
    }
}
