package com.example.whatsappdireto.controller

import android.content.Context
import com.example.whatsappdireto.database.RoomDB
import com.example.whatsappdireto.database.entity.Contato
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class ContatoController(
    private val context: Context,

    ) {
    private val roomDB = RoomDB.recuperarInstanciaRoom(context)
    private val contatoDAO = roomDB.contatoDAO

    suspend fun adicionarContato(contato: Contato) = CoroutineScope(Dispatchers.IO).launch {
        if (contato.telefone.minLength(10)) {
            contatoDAO.salvar(contato)
        }
    }

    suspend fun listarUsuarios(): List<Contato> = withContext(Dispatchers.IO) {
        contatoDAO.listar()
    }
}


