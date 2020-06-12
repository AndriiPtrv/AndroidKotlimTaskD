package com.example.androidkotlintaskd.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DrinkDB::class], version = 1, exportSchema = false)
abstract class DrinkDataBase: RoomDatabase() {

    abstract fun getDrinkDBDao(): DrinkDBDao

    companion object{
        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DrinkDataBase::class.java,
            "drinks"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}