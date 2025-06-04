package com.example.estoque.database

import com.example.estoque.MyApp
import com.example.estoque.model.Produto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseController {
    val db = DatabaseBuilder.getInstance(MyApp.instance.applicationContext)
    val produtoDao = db.produtoDao()

    fun criarProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            produtoDao.inserir(produto)
        }
    }

    fun atualizarProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            produtoDao.atualizar(produto)
        }
    }

    fun deletarProduto(produto: Produto, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            produtoDao.deletar(produto)
            withContext(Dispatchers.Main) {
                callback()
            }
        }
    }

    fun listarProdutos(callbackSuccess: (MutableList<Produto>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val lista = produtoDao.listarTodos()
            withContext(Dispatchers.Main) {
                callbackSuccess(lista)
            }
        }
    }
}