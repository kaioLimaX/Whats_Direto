package com.example.whatsappdireto.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.FragmentHistoricoBinding
import com.example.whatsappdireto.extensions.OnClickListenerListener
import com.example.whatsappdireto.extensions.VcardUtil
import com.example.whatsappdireto.extensions.VcardUtil.contatoParaVCard
import com.example.whatsappdireto.extensions.openWhatsAppChat
import com.example.whatsappdireto.view.adapter.ContatosAdapter
import kotlinx.coroutines.launch
import java.io.File


class HistoricoFragment : Fragment(),OnClickListenerListener {

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

    //inicialização do recyclerView

    private fun iniciarRecyclerView() {
        adapter = ContatosAdapter(requireContext(), mutableListOf(),this)
        binding.rvContatos.adapter = adapter
        binding.rvContatos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContatos.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        lifecycleScope.launch {
            val listaContatos = controller.listarUsuarios()
            if(listaContatos.isEmpty()){

                binding.cvListaVazia.visibility = View.VISIBLE

            }else{
                binding.cvListaVazia.visibility = View.INVISIBLE
                adapter.atualizarContatos(listaContatos)
            }
        }


    }

    override fun onClicar(contato: Contato) {
        view?.openWhatsAppChat(contato.telefone)
    }

    //controle da interface do adapter

    override fun onEditar(valorEditText: String, contato: Contato) {
        if(contato.nome.isNotBlank()){
            controller.atualizarContato(contato)
            adapter.alterarContato(contato)
        }
    }

    override fun onCompartilhar(contato: Contato) {

       /* para criar o botao compartilhar em vcf, você precisa criar o VCardUtil que esta nas extenções, um arquivo no res/xml
       * para memorizar a pasta, e colocar o fileProvider no manifest
       */

        val vcardString = contatoParaVCard(contato)
        val nomeArquivo = "contato_${contato.nome}.vcf"

        val arquivoVCard = File(requireContext().cacheDir, nomeArquivo)

        // Escreve o conteúdo do vCard no arquivo
        arquivoVCard.writeText(vcardString)

        // Obtém um URI seguro usando o FileProvider
        val uri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", arquivoVCard)

        // Cria um Intent para compartilhar o arquivo
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/vcard"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        // Inicia a atividade de compartilhamento
        requireContext().startActivity(Intent.createChooser(intent, "Compartilhar Contato"))
    }


    override fun onExcluir(contato: Contato) {
        controller.removerContato(contato)
        adapter.removerContato(contato)
    }


}