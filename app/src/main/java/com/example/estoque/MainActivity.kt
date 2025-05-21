package com.example.estoque

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        //Criar dados falsos
        val listaFake = mutableListOf(
            Produto("Pão", 56),
            Produto("Hambúrguer", 54),
            Produto("Queijo", 43),
            Produto("Alface", 12),
            Produto("Tomate", 23),
            Produto("Bacon", 2),
            Produto("Molho especial", 24),
            Produto("Salsicha", 56),
            Produto("Laranja", 54),
            Produto("Açaí", 43),
            Produto("Cebola", 12),
            Produto("Detergente", 23),
            Produto("Papel higiênico", 2),
            Produto("Papel toalha", 24),
        )

        //Adapter com os dados
        val adapter = ProdutoAdapter(listaFake)
        recyclerView.adapter = adapter

        //Layout vertical
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}