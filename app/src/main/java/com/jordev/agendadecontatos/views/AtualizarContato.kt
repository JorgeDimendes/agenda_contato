package com.jordev.agendadecontatos.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jordev.agendadecontatos.AppDatabase
import com.jordev.agendadecontatos.ChangeStatusBarTextColor
import com.jordev.agendadecontatos.componentes.CustomButton
import com.jordev.agendadecontatos.componentes.CustomTextField
import com.jordev.agendadecontatos.dao.ContatoDao
import com.jordev.agendadecontatos.ui.theme.PURPLE500
import com.jordev.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var contatoDao: ContatoDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtualizarContato(navController: NavController, uid: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }


    //Configuração da cor do tema letra/icon
    ChangeStatusBarTextColor(false)

    Scaffold (
        topBar = {
            TopAppBar(
                expandedHeight = 40.dp,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE500,
                    titleContentColor = WHITE
                ),
                title = {Text(text = "Atualizar Contato")}
            )
        }
    ){paddingValues ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            CustomTextField(
                valueId = nome,
                onValueChangeId = {
                    nome = it
                },
                labelId = "Nome",
                leadingIcon = Icons.Default.Person,
                keyboardOptionsId = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences //Primeira letra maiúscula
                )
            )
            CustomTextField(
                valueId = sobrenome,
                onValueChangeId = {
                    sobrenome = it
                },
                labelId = "Sobrenome",
                leadingIcon = Icons.Default.Person,
                keyboardOptionsId = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences //Primeira letra maiúscula
                )
            )
            CustomTextField(
                valueId = idade,
                onValueChangeId = {
                    idade = it
                },
                labelId = "Idade",
                leadingIcon = Icons.Default.Person,
                keyboardOptionsId = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            CustomTextField(
                valueId = celular,
                onValueChangeId = {
                    celular = it
                },
                labelId = "Celular",
                modifierId = Modifier,
                leadingIcon = Icons.Default.Phone,
                keyboardOptionsId = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            CustomButton(
                onClickId = {
                    var mensagem = false

                    scope.launch (Dispatchers.IO){

                        if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()){
                            mensagem = false
                        }else{
                            mensagem = true
                            contatoDao = AppDatabase.getInstance(context).contatoDao()
                            contatoDao.atualizar(uid.toInt(), nome, sobrenome, idade, celular)
                        }
                    }
                    scope.launch(Dispatchers.Main){
                        if (mensagem){
                            Toast.makeText(context, "Contato Atualizado com sucesso", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else{
                            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifierId = Modifier,
                txt = "Atualizar"
            )
        }
    }
    
}