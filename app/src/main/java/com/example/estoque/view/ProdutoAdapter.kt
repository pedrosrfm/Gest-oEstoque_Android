package com.example.estoque.view

import android.icu.text.UnicodeSetIterator
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.estoque.R
import com.example.estoque.model.Produto

class ProdutoAdapter(
    private var produtos: MutableList<Produto>,
    private val onUpdate: (Produto) -> Unit,
    private val onDelete: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    fun setList(produtos: MutableList<Produto>) {
        this.produtos = produtos
        notifyDataSetChanged()
    }

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeProduto: TextView = itemView.findViewById(R.id.tvNomeProduto)
        val tvQuantidade: TextView = itemView.findViewById(R.id.tvQuantidade)
        val btnAdicionar: ImageButton = itemView.findViewById(R.id.btnAdicionar)
        val btnRemover: ImageButton = itemView.findViewById(R.id.btnRemover)
        val btnDeletar: ImageButton = itemView.findViewById(R.id.btnDeletar)

    }

    fun atualizarLista(novaLista: MutableList<Produto>) {
        produtos.clear()
        produtos.addAll(novaLista)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.tvNomeProduto.text = "${produto.nome}"
        holder.tvQuantidade.text = "${produto.quantidade}"

        holder.btnAdicionar.setOnClickListener {
            it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(50).start()
            }.start()
            produto.quantidade++
            onUpdate(produto)
            notifyItemChanged(position)
        }

        holder.btnAdicionar.setOnLongClickListener {
            val context = it.context
            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.filters = arrayOf(InputFilter.LengthFilter(3))
            input.hint = "Quantidade"

            AlertDialog.Builder(context)
                .setTitle("Adicionar quantidade")
                .setMessage("Informe a quantidade a adicionar:")
                .setView(input)
                .setPositiveButton("Adicionar") { dialog, which ->
                    val quantidade = input.text.toString().toIntOrNull() ?: 0
                    if (quantidade > 0) {
                        produto.quantidade += quantidade
                        onUpdate(produto)
                        notifyItemChanged(holder.adapterPosition)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()

            true
        }

        holder.btnRemover.setOnClickListener {
            it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(50).start()
            }.start()

            if (produto.quantidade > 0) {
                produto.quantidade--
                onUpdate(produto)
            }
            notifyItemChanged(position)
        }

        holder.btnRemover.setOnLongClickListener {
            val context = it.context
            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.filters = arrayOf(InputFilter.LengthFilter(3))
            input.hint = "Quantidade"

            AlertDialog.Builder(context)
                .setTitle("Remover quantidade")
                .setMessage("Informe a quantidade a remover:")
                .setView(input)
                .setPositiveButton("Remover") { dialog, which ->
                    val quantidade = input.text.toString().toIntOrNull() ?: 0
                    if (quantidade > 0) {
                        produto.quantidade -= quantidade
                        if (produto.quantidade < 0) {
                            produto.quantidade = 0
                        }
                        onUpdate(produto)
                        notifyItemChanged(holder.adapterPosition)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()

            true
        }

        if (produto.quantidade == 0) {
            holder.btnRemover.isEnabled = false
            holder.btnRemover.alpha = 0.3f
        } else {
            holder.btnRemover.isEnabled = true
            holder.btnRemover.alpha = 1.0f
        }

        holder.btnDeletar.setOnClickListener {
            it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(50).start()
                AlertDialog.Builder(it.context)
                    .setTitle("Confirmação")
                    .setMessage("Tem certeza que deseja deletar o produto \"${produto.nome}\"?")
                    .setPositiveButton("Sim") { dialog, which ->
                        val position = holder.adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            onDelete(produto)
                        }
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }.start()
        }
    }

    override fun getItemCount() = produtos.size
}
