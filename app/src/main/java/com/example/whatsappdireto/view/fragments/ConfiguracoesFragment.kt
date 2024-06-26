package com.example.whatsappdireto.view.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.whatsappdireto.R
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.controller.SharedController
import com.example.whatsappdireto.databinding.FragmentConfiguracoesBinding
import com.example.whatsappdireto.extensions.AlertExcluir
import com.example.whatsappdireto.sharedPreferences.PreferencesManager
import java.net.URLEncoder

class ConfiguracoesFragment : Fragment() {

    private val preferencesManager: PreferencesManager by lazy {
        PreferencesManager(requireContext())
    }

    private val sharedController: SharedController by lazy {
        SharedController(preferencesManager)
    }

    private val controller by lazy {
        ContatoController(requireContext())
    }



    private var _binding: FragmentConfiguracoesBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracoesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniciarSwith()

        binding.btnReportar.setOnClickListener {
            abrirEmail("caiooalexandre95@gmail.com")
        }
        binding.btnPoliticas.setOnClickListener {
            abrirLink("https://imobilead.me/lps/politica/?nome=Zap%20Direto")
        }

        binding.btnExcluirTudo.setOnClickListener {
            val meuNovoAlert = AlertExcluir(requireContext())
            meuNovoAlert.exibir(
                titulo = "Excluir Historico",
                mensagem = "Deseja excluir todos os contatos do historico?.",
                textoBotaoConfirmar = "Excluir",
                onConfirmar = {
                    controller.removerHistorico()
                }
            )
        }


    }

    private fun iniciarSwith() {


        val switch = binding.swithSalvar

        val checkedColor = ContextCompat.getColor(requireContext(), R.color.color_primary)
        val uncheckedColor =
            ContextCompat.getColor(requireContext(), R.color.switchTrackUncheckedColor)


        switch.thumbTintList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(checkedColor, uncheckedColor)
        )

        val isEnabled = sharedController.getDefaultNumber() == 1
        switch.isChecked = isEnabled

        switch.setOnCheckedChangeListener { _, isChecked ->
            val newValue = if (isChecked) 1 else 0
            preferencesManager.setDefaultNumber(newValue)
        }
    }

    fun abrirEmail(destinatario: String) {
        val assunto = "Reportar problema"


        val uri = Uri.parse("mailto:$destinatario?subject=${URLEncoder.encode(assunto, "UTF-8").replace("+", "%20")}")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        // Verifica se há um aplicativo capaz de lidar com o Intent
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            // Lida com o caso em que nenhum aplicativo de email está instalado
            Toast.makeText(requireContext(), "Nenhum aplicativo de email encontrado.", Toast.LENGTH_SHORT).show()
        }
    }

    fun abrirLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }



}