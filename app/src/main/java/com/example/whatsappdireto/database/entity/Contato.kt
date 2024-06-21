package com.example.whatsappdireto.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Entity(
    tableName = "contatos"
)
@TypeConverters(Conversor::class)
class Contato (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contato")
    val idContato: Long,
    @ColumnInfo(defaultValue = "")
    val nome: String,
    val telefone: String,
    val dataCadastro: Date?,
)

@ProvidedTypeConverter
class Conversor {
    @TypeConverter()
    fun convertToLong(date: Date): Long? {
        return date?.time
    }

    @TypeConverter()
    fun convertToDate(date: Long): Date? {
        return date.let { dtLong ->
            Date(date)
        }
    }
}
