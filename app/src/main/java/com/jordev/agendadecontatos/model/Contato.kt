package com.jordev.agendadecontatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jordev.agendadecontatos.constant.Constantes

@Entity(tableName = Constantes.TABELA_CONTATOS)
data class Contato(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "nome") val nome: String?,
    @ColumnInfo(name = "sobrenome") val sobrenome: String?,
    @ColumnInfo(name = "idade") val idade: Int?,
    @ColumnInfo(name = "celular") val celular: Int?,
)