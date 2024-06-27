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
import com.example.whatsappdireto.controller.SharedController
import com.example.whatsappdireto.databinding.FragmentConfiguracoesBinding
import com.example.whatsappdireto.sharedPreferences.PreferencesManager

class ConfiguracoesFragment : Fragment() {

    private val preferencesManager: PreferencesManager by lazy {
        PreferencesManager(requireContext())
    }

    private val sharedController: SharedController by lazy {
        SharedController(preferencesManager)
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
            openGmailWithSubject("Reportar um problema")
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

    fun openGmailWithSubject(subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Especifica que é um intent para enviar e-mail
            putExtra(Intent.EXTRA_SUBJECT, subject) // Define o assunto do e-mail
            putExtra(Intent.EXTRA_EMAIL, arrayOf("caiooalexandre95@gmail.com")) // Adicione um e-mail se quiser pré-preencher o destinatário
        }

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            // Caso não encontre um aplicativo de e-mail compatível
            Toast.makeText(requireContext(), "Nenhum aplicativo de e-mail encontrado", Toast.LENGTH_SHORT).show()
        }
    }


}