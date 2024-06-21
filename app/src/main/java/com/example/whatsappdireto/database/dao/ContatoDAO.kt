package com.example.whatsappdireto.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.whatsappdireto.database.entity.Contato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDAO {

    @Insert
    fun salvar(contato: Contato)

    @Delete
    fun remover(contato: Contato)

    @Update
    fun atualizar(contato: Contato)

    @Query("SELECT * FROM contatos ")
    fun listar(): List<Contato>
}