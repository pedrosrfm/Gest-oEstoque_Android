package com.example.estoque

import androidx.room.*

@Dao
interface ProdutoDao {

    @Insert
    suspend fun inserir(produto: Produto)

    @Update
    suspend fun atualizar(produto: Produto)

    @Delete
    suspend fun deletar(produto: Produto)

    @Query("SELECT * FROM produtos ORDER BY nome ASC")
    suspend fun listarTodos(): MutableList<Produto>

}