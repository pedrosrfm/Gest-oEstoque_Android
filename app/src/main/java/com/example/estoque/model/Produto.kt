package com.example.estoque

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "produtos")
data class Produto(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "nome")
    val nome: String,

    @ColumnInfo(name = "quantidade")
    var quantidade: Int = 0,

    @ColumnInfo(name = "tipo")
    val tipoProduto: TipoProduto
)
