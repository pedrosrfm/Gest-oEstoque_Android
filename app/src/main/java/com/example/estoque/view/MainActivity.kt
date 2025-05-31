package com.example.estoque.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estoque.R
import com.example.estoque.database.DatabaseController
import com.example.estoque.model.Produto
import com.example.estoque.model.TipoProduto

class MainActivity : AppCompatActivity() {

    val dbController = DatabaseController()
    val adapter = ProdutoAdapter(mutableListOf<Produto>(), ::onUpdate, ::onDelete)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        dbController.criarProduto(Produto(nome = "Feijão", quantidade = 32, tipoProduto = TipoProduto.ALIMENTO))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = adapter
        atualizarProdutos()
        //Layout vertical
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun onUpdate(produto: Produto) {
        dbController.atualizarProduto(produto)
    }

    fun onDelete(produto: Produto) {
        dbController.deletarProduto(produto)
        atualizarProdutos()
    }

    fun atualizarProdutos() {
        dbController.listarProdutos { lista ->
            adapter.setList(lista)
        }
    }
}