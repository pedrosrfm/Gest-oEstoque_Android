package com.example.estoque

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
}