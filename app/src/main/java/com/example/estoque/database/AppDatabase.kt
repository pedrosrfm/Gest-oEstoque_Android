package com.example.estoque.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.estoque.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
}