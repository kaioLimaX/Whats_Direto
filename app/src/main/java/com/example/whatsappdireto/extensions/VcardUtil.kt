package com.example.whatsappdireto.extensions

import com.example.whatsappdireto.database.entity.Contato

object VcardUtil {
    fun contatoParaVCard(contato: Contato): String {
        val vcard = StringBuilder()
        vcard.append("BEGIN:VCARD\n")
        vcard.append("VERSION:3.0\n")
        vcard.append("N:${contato.nome};;;\n")
        vcard.append("FN:${contato.nome}\n")
        vcard.append("TEL;TYPE=CELL:${contato.telefone}\n")
        // Adicione outros campos do contato, se necessário
        vcard.append("END:VCARD")
        return vcard.toString()
    }
}