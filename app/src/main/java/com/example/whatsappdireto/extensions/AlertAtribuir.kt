package com.example.whatsappdireto.extensions

import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.LayoutAtribuirNomeBinding

class AlertAtribuir(
    private val context: Context
) {

    private val binding = LayoutAtribuirNomeBinding.inflate(LayoutInflater.from(context))
    private val viewCarregamento = binding.root

    private var alertDialog: AlertDialog? = null


    fun exibir(titulo: String,
               contato: Contato,
               onClickListenerListener: OnClickListenerListener){
        val alertBuilder = AlertDialog.Builder( context)
            .setTitle(SpannableString("Alterar $titulo ?").apply {
                setSpan(StyleSpan(Typeface.BOLD),0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            })
            .setCancelable( false )
            .setView( viewCarregamento )
            .setNegativeButton("cancelar"){dialog, _ ->
                fechar()
            }.setPositiveButton(SpannableString("salvar").apply { // Crie um SpannableString para o botão
                setSpan(StyleSpan(Typeface.BOLD), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Aplique o estilo negrito
            }) { dialog, _ ->
                val nome = binding.txtNomeContato.text.toString()
                onClickListenerListener.onSalvar(nome,contato)
                fechar()
            }

        alertDialog = alertBuilder.create()
        alertDialog?.show()
    }

    fun fechar(){
        alertDialog?.setOnDismissListener {
            val viewGroup = viewCarregamento.parent as ViewGroup
            viewGroup.removeView( viewCarregamento )
        }
        alertDialog?.dismiss()
    }

}