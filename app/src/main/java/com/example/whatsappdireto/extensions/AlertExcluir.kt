package com.example.whatsappdireto.extensions

import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

class AlertExcluir(private val context: Context) {

    private var alertDialog: AlertDialog? = null

    fun exibir(
        titulo: String,mensagem: String?, // Opcional, para exibir uma mensagem
        textoBotaoConfirmar: String = "Confirmar", // Texto padrão do botão
        onConfirmar: () -> Unit // Função a ser executada ao confirmar
    ) {
        val alertBuilder = AlertDialog.Builder(context)
            .setTitle(SpannableString(titulo).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            })
            .setCancelable(false)
            .setNegativeButton("Cancelar") { dialog, _ ->fechar()
            }
            .setPositiveButton(SpannableString(textoBotaoConfirmar).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }) { dialog, _ ->
                onConfirmar() // Executa a função passada como parâmetro
                fechar()
            }

        // Define a mensagem, se fornecida
        mensagem?.let { alertBuilder.setMessage(it) }

        alertDialog = alertBuilder.create()
        alertDialog?.show()
    }

    fun fechar() {
        alertDialog?.dismiss()
    }
}