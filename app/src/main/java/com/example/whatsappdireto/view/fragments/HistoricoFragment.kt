package com.example.whatsappdireto.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsappdireto.R
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.databinding.FragmentHistoricoBinding
import com.example.whatsappdireto.databinding.FragmentHomeBinding
import com.example.whatsappdireto.view.adapter.ContatosAdapter
import kotlinx.coroutines.launch


class HistoricoFragment : Fragment() {
    private val controller by lazy {
        ContatoController(requireContext())
    }

    private var _binding: FragmentHistoricoBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: ContatosAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniciarRecyclerView()


    }

    private fun iniciarRecyclerView() {
        adapter = ContatosAdapter(emptyList())
        binding.rvContatos.adapter = adapter
        binding.rvContatos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContatos.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        lifecycleScope.launch {
            val contatos = controller.listarUsuarios()
            adapter.atualizarContatos(contatos)
        }


    }


}