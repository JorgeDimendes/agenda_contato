package com.jordev.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jordev.agendadecontatos.AppDatabase
import com.jordev.agendadecontatos.ChangeStatusBarTextColor
import com.jordev.agendadecontatos.dao.ContatoDao
import com.jordev.agendadecontatos.itemlista.ContatoItem
import com.jordev.agendadecontatos.model.Contato
import com.jordev.agendadecontatos.ui.theme.PURPLE500
import com.jordev.agendadecontatos.ui.theme.WHITE
import com.jordev.agendadecontatos.viewmodel.ContatoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var contatoDao: ContatoDao

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaContatos(navController: NavController) {
    ChangeStatusBarTextColor(darkIcons = false) // Ícones e texto escuros
    val contatoViewModel: ContatoViewModel = hiltViewModel()

    val context = LocalContext.current
    val listaContatos = remember { mutableStateListOf<Contato>() } // Correção
    val contatoDao = remember { AppDatabase.getInstance(context).contatoDao() } // Correção


    // Carregar contatos uma única vez
    LaunchedEffect(Unit) {
        val contatos = contatoDao.getContatos()
        listaContatos.clear()
        listaContatos.addAll(contatos)
    }
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

        LazyColumn(modifier = Modifier.padding(paddingvalue).padding(bottom = 50.dp)) {
            itemsIndexed(listaContatos) {index, item ->
                ContatoItem(
                    navController = navController,
                    position = index,
                    listaContatos = listaContatos,
                    context = context,
                    contatoViewModel = contatoViewModel
                )
            }
        }
    }
}
