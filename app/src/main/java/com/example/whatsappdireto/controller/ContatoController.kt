package com.example.whatsappdireto.controller

import android.content.Context
import androidx.room.Room
import com.example.whatsappdireto.database.RoomDB
import com.example.whatsappdireto.database.dao.ContatoDAO
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class ContatoController(
    private val context: Context,

    ) {
    val roomDB = RoomDB.recuperarInstanciaRoom(context)
    val contatoDAO = roomDB.contatoDAO

    fun adicionarContato(contato: Contato) = CoroutineScope(Dispatchers.IO).launch {
        contatoDAO.salvar(contato)
    }

    fun listarcontatos() : List<Contato> = contatoDAO.listar()


}