package com.example.whatsappdireto.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "contatos"
)
class Contato (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contato")
    val idContato: Long,
    @ColumnInfo(defaultValue = "")
    val nome: String,
    val telefone: String
)