package com.jordev.agendadecontatos.itemlista

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.jordev.agendadecontatos.dao.ContatoDao
import com.jordev.agendadecontatos.model.Contato
import com.jordev.agendadecontatos.ui.theme.BLACK
import com.jordev.agendadecontatos.ui.theme.RED
import com.jordev.agendadecontatos.ui.theme.WHITE

//@Preview(showBackground = true)
@Composable
fun ContatoItem(
    navController: NavController,
    position: Int,
    listaContatos: MutableList<Contato>,
    context: Context
){
//    val contato: MutableList<ContatoDao> = mutableListOf()
    val nome = listaContatos[position].nome
    val sobrenome = listaContatos[position].sobrenome
    val idade = listaContatos[position].idade
    val celular = listaContatos[position].celular
    val uid = listaContatos[position].uid

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = WHITE,
            contentColor = BLACK
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val (txtNome, txtIdade, txtCelular, btAtualizar, btDeletar) = createRefs()
            Text(
                text = "Contato: $nome $sobrenome",
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(txtNome) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Idade: $idade",
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(txtIdade) {
                    top.linkTo(txtNome.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Numero: $celular",
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(txtCelular) {
                    top.linkTo(txtIdade.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )
            Icon(
                imageVector = Icons.Default.Edit, contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navController.navigate("atualizarContato/${uid}")
                    }
                    .constrainAs(btAtualizar) {
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                        end.linkTo(btDeletar.start, margin = 25.dp)
                    }
            )

            Icon(
                imageVector = Icons.Default.Delete, contentDescription = null,
                modifier = Modifier
                    .clickable {

                    }
                    .constrainAs(btDeletar) {
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    },
                tint = RED
            )
        }
    }
}