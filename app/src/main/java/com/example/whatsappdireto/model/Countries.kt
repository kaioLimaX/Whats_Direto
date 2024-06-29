package com.example.whatsappdireto.model

data class Countries(
    val codigo: String,
    val fone: String,
    val iso: String,
    val iso3: String,
    val nome: String,
    val nomeFormal: String
){
    override fun toString(): String{
        return "$iso3 +${fone.toInt()}" // Exibe ambos os índices no título
    }
}