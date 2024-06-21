package com.example.whatsappdireto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsappdireto.R
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.FragmentHomeBinding
import com.example.whatsappdireto.masks.MaskEditTextChangedListener


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

        binding.btnOpenChat.setOnClickListener {

            val contato = Contato(
                0,
                "caio",
                "35991331230"
            )
            controller.adicionarContato(contato)
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