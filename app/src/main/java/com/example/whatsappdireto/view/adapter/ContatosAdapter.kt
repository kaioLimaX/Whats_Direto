package com.example.whatsappdireto.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappdireto.database.entity.Contato
import com.example.whatsappdireto.databinding.ItemContatoBinding
import com.example.whatsappdireto.extensions.AlertAtribuir
import com.example.whatsappdireto.extensions.OnClickListenerListener
import java.text.SimpleDateFormat
import java.util.Locale


class ContatosAdapter(
    context: Context,
    private var listaContatos: MutableList<Contato>,
    private val onClickListenerListener: OnClickListenerListener
) :
    RecyclerView.Adapter<ContatosAdapter.ContatoViewHolder>() {

    private val alertAtribuir = AlertAtribuir(context)


    class ContatoViewHolder(val binding: ItemContatoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contato: Contato, adapter: ContatosAdapter) {
            if (contato.nome == "") {
                binding.txtNome.text = contato.telefone
            } else {
                binding.txtNome.text = contato.nome

            }
            val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dataFormatada = formato.format(contato.dataCadastro)
            binding.txtData.text = dataFormatada

            binding.itemContato.setOnClickListener {
                adapter.onClickListenerListener.onClicar(contato)
            }

            binding.btnAtribuir.setOnClickListener {
                adapter.alertAtribuir.exibir(contato.telefone, contato,adapter.onClickListenerListener)
            }
            binding.btnExcluir.setOnClickListener {
                adapter.onClickListenerListener.onExcluir(contato)
            }
            binding.btnCompartilhar.setOnClickListener {
                adapter.onClickListenerListener.onCompartilhar(contato)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val binding = ItemContatoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContatoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = listaContatos[position]
        holder.bind(contato, this)
    }

    override fun getItemCount(): Int = listaContatos.size

    fun atualizarContatos(novaLista: List<Contato>) {
        listaContatos = novaLista.toMutableList()
        notifyDataSetChanged()
    }
    fun removerContato(contato : Contato) {
        val position = listaContatos.indexOfFirst { it.idContato == contato.idContato }
        listaContatos.removeAt(position)
        notifyItemRemoved(position)
    }
    fun alterarContato(contato: Contato) {
        val position = listaContatos.indexOfFirst { it.idContato == contato.idContato }
        if (position != -1) {
            listaContatos[position] = contato
            notifyItemChanged(position)
        }
    }

}