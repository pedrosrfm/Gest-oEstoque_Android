package com.example.estoque

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

//        // Cria a instância do banco de dados
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "estoque.db"
//        ).build()
//
//        // Cria a instância do DAO
//        val produtoDao = db.produtoDao()


        //Criar dados falsos
        val listaFake = mutableListOf(
            Produto(null, "Pão", 56, TipoProduto.ALIMENTO),
            Produto(null, "Hambúrguer", 54, TipoProduto.ALIMENTO),
            Produto(null, "Queijo", 43, TipoProduto.ALIMENTO),
            Produto(null, "Alface", 12, TipoProduto.ALIMENTO),
            Produto(null, "Tomate", 23, TipoProduto.ALIMENTO),
            Produto(null, "Bacon", 2, TipoProduto.ALIMENTO),
            Produto(null, "Molho especial", 24, TipoProduto.ALIMENTO),
            Produto(null, "Salsicha", 56, TipoProduto.ALIMENTO),
            Produto(null, "Laranja", 54, TipoProduto.ALIMENTO),
            Produto(null, "Açaí", 43, TipoProduto.ALIMENTO),
            Produto(null, "Cebola", 12, TipoProduto.ALIMENTO),
            Produto(null, "Detergente", 23, TipoProduto.PRODUTO_DE_LIMPEZA),
            Produto(null, "Papel higiênico", 2, TipoProduto.PRODUTO_DE_LIMPEZA),
            Produto(null, "Papel toalha", 24, TipoProduto.PRODUTO_DE_LIMPEZA),
            Produto(null, "Coca Lata", 34, TipoProduto.BEBIDA)
        )

        //Adapter com os dados
        val adapter = ProdutoAdapter(listaFake)
        recyclerView.adapter = adapter

        //Layout vertical
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}