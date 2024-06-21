package com.example.whatsappdireto.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.whatsappdireto.R
import com.example.whatsappdireto.Utils
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.FragmentHomeBinding
import com.example.whatsappdireto.masks.MaskEditTextChangedListener
import kotlinx.coroutines.launch
import java.util.Date


class HomeFragment : Fragment() {

    private val controller by lazy {
        ContatoController(requireContext())
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       iniciarMascara()
        iniciarEventosClique()


    }

    private fun iniciarEventosClique() {
        binding.btnOpenChat.setOnClickListener {

            val numero = binding.edtPhone.text.toString()
            val numeroFormatado = Utils.unmask(numero, setOf("(", ")", "-", " "))

            val contato = Contato(
                0,
                "",
                numeroFormatado,
                Date()
            )
            lifecycleScope.launch {
                controller.adicionarContato(contato)
            }
        }
    }

    private fun iniciarMascara() {
        val editText = binding.edtPhone
        val maskPhone = MaskEditTextChangedListener(
            "(##) #####-####",
            editText
        )
        editText.addTextChangedListener(maskPhone)

    }


}