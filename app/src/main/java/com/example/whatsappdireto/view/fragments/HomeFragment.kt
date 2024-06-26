package com.example.whatsappdireto.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import com.example.utils.JsonUtil
import com.example.utils.Utils
import com.example.whatsappdireto.R
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.controller.SharedController
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.FragmentHomeBinding
import com.example.whatsappdireto.extensions.openWhatsAppChat
import com.example.whatsappdireto.masks.MaskEditTextChangedListener
import com.example.whatsappdireto.sharedPreferences.PreferencesManager
import com.example.whatsappdireto.view.adapter.SpinnerCountriesAdapter
import kotlinx.coroutines.launch
import java.util.Date


class HomeFragment : Fragment() {

    private val controller by lazy {
        ContatoController(requireContext())
    }

    private val preferencesManager: PreferencesManager by lazy {
        PreferencesManager(requireContext())
    }

    private val sharedController: SharedController by lazy {
        SharedController(preferencesManager)
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
       carregarListaPaises()


    }

    private fun carregarListaPaises() {
        val listaPaises = JsonUtil.getPaisesFromAsset(requireContext(), "countries.json")!!

        val adapter = SpinnerCountriesAdapter(requireContext(), android.R.layout.simple_spinner_item, listaPaises)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = binding.spinner
        spinner.adapter = adapter

       /* for (pais in listaPaises!!) {
            Log.i("info_ddi", "carregarListaPaises: ${pais.nome} - ${pais.fone} ")
        }*/
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
                if (sharedController.getDefaultNumber() == 1) {
                    Log.i("info_shared", "iniciarEventosClique: salvar ")
                    controller.adicionarContato(contato)
                }

            }
            it.openWhatsAppChat(contato.telefone)
            binding.edtPhone.setText("")
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