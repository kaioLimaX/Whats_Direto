package com.example.whatsappdireto.extensions

import com.example.whatsappdireto.database.entity.Contato

interface OnClickListenerListener {
    fun onClicar(contato: Contato)
    fun onEditar(valorEditText: String, contato: Contato)
    fun onCompartilhar(contato: Contato)
    fun onExcluir(contato: Contato)
}