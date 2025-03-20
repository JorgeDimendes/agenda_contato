package com.jordev.agendadecontatos.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.jordev.agendadecontatos.AppDatabase
import com.jordev.agendadecontatos.dao.ContatoDao
import com.jordev.agendadecontatos.model.Contato
import com.jordev.agendadecontatos.ui.theme.BLACK
import com.jordev.agendadecontatos.ui.theme.RED
import com.jordev.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao
@Composable
fun ContatoItem(
    navController: NavController,
    position: Int,
    listaContatos: MutableList<Contato>,
    context: Context
){
    val scope = rememberCoroutineScope()
    val nome = listaContatos[position].nome
    val sobrenome = listaContatos[position].sobrenome
    val idade = listaContatos[position].idade
    val celular = listaContatos[position].celular
    val uid = listaContatos[position].uid

    val contato = listaContatos[position]

    fun alertDialogDeletarContato(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deseja Excluir o Contato $nome $sobrenome?")
            .setMessage("Tem certeza?")
        alertDialog.setPositiveButton("Sim"){_,_ ->
            scope.launch(Dispatchers.IO){
                contatoDao = AppDatabase.getInstance(context).contatoDao()
                contatoDao.deletar(uid)
                listaContatos.remove(contato)
            }
            scope.launch(Dispatchers.Main) {
                navController.navigate("listaContatos")
                Toast.makeText(context, "Contato excluído com sucesso", Toast.LENGTH_SHORT).show()
            }
        }
        alertDialog.setNegativeButton("Não") { _, _ ->

        }
        alertDialog.show()
    }
//Comentario
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
                        alertDialogDeletarContato()
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