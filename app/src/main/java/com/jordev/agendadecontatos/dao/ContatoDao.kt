package com.jordev.agendadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jordev.agendadecontatos.model.Contato

@Dao
interface ContatoDao {

    //Inserir
    @Insert
    fun gravar(listaContatos: MutableList<Contato>)

    //Recuperar
    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun getContatos(): MutableList<Contato>

    //Atualizar
    @Query("UPDATE tabela_contatos SET nome = :novoNome, sobrenome = :novoNobrenome, idade = :novaIdade, celular = :novoCelular " +
            "WHERE uid = :id")
    fun atualizar(id: Int, novoNome: String, novoNobrenome: String, novaIdade: String, novoCelular: String)

    //Deletar
    @Query("DELETE FROM tabela_contatos WHERE uid = :id")
    fun deletar(id: Int)


}