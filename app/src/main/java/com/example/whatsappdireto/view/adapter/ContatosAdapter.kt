package com.example.whatsappdireto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.ItemContatoBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ContatosAdapter(private var listaContatos: List<Contato>) :
    RecyclerView.Adapter<ContatosAdapter.ContatoViewHolder>() {

    class ContatoViewHolder(val binding: ItemContatoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contato: Contato) {
            if(contato.nome == ""){
                binding.txtNome.text = contato.telefone
            }else{
                binding.txtNome.text = contato.nome
            }
            val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dataFormatada = formato.format(contato.dataCadastro)
            binding.txtData.text = dataFormatada
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val binding = ItemContatoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContatoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = listaContatos[position]
        holder.bind(contato)
    }

    override fun getItemCount(): Int = listaContatos.size

    fun atualizarContatos(novaLista: List<Contato>) {
        listaContatos = novaLista
        notifyDataSetChanged()
    }
}