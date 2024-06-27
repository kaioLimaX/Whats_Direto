package com.example.whatsappdireto.view.fragments

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    }

    private fun iniciarSwith() {


        val switch = binding.swithSalvar
        val checkedColor = ContextCompat.getColor(requireContext(), R.color.color_primary)

        // Define a cor da bolinha quando unchecked
        val uncheckedColor = ContextCompat.getColor(requireContext(), R.color.switchTrackUncheckedColor)

        // Configura a cor da bolinha com base no estado checked
        switch.thumbTintList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
            intArrayOf(checkedColor, uncheckedColor)
        )

        val isEnabled = sharedController.getDefaultNumber() == 1
        switch.isChecked = isEnabled

        switch.setOnCheckedChangeListener { _, isChecked ->
            val newValue = if (isChecked) 1 else 0
            preferencesManager.setDefaultNumber(newValue)
        }
    }


}