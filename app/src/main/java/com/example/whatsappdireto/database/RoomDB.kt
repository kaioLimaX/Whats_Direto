package com.example.whatsappdireto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whatsappdireto.database.dao.ContatoDAO
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.database.entity.Conversor

@Database(
    entities = [
        Contato::class
    ],
    version = 1,

    )
abstract class RoomDB : RoomDatabase() {
    abstract val contatoDAO: ContatoDAO


    companion object {


        //função getInstance()
        fun recuperarInstanciaRoom(context: Context): RoomDB {

            return Room.databaseBuilder(
                context,
                RoomDB::class.java,
                "projeto.db"            )
                .addTypeConverter(Conversor())
                .build()
        }
    }
}