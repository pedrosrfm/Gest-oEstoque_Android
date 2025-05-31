package com.example.estoque.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.internal.synchronized

object DatabaseBuilder {

    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db"
                ).build()
        }
        return instance!!
    }
}