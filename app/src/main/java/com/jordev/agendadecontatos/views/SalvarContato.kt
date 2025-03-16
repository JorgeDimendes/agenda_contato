package com.jordev.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jordev.agendadecontatos.componentes.CustomTextField
import com.jordev.agendadecontatos.ui.theme.PURPLE500
import com.jordev.agendadecontatos.ui.theme.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SalvarContato(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }



    Scaffold (
        topBar = {
            TopAppBar(
                expandedHeight = 40.dp,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE500,
                    titleContentColor = WHITE
                ),
                title = {Text(text = "Salvar Novo Contato")}
            )
        }

    ){paddingValues ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CustomTextField(
                valueId = nome,
                onValueChangeId = {
                    nome = it
                },
                labelId = "Nome",
                modifierId = Modifier,
                leadingIcon = Icons.Default.Person,
                //            keyboardOptionsId = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                modifierId = Modifier,
                leadingIcon = Icons.Default.Person,
                //            keyboardOptionsId = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                modifierId = Modifier,
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

        }
    }

}