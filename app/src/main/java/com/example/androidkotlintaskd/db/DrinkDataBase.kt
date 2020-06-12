package com.example.androidkotlintaskd.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.db.model.InfoDrinkDB

@Database(entities = [DrinkDB::class, InfoDrinkDB::class], version = 2, exportSchema = false)
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