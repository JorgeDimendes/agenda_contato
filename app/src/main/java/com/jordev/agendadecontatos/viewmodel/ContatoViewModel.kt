package com.jordev.agendadecontatos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordev.agendadecontatos.model.Contato

class ContatoViewModel: ViewModel()  {
    val contatoSelecionado = MutableLiveData<Contato>()

    fun selecionarContato(contato: Contato){
        contatoSelecionado.value = contato

    }
}