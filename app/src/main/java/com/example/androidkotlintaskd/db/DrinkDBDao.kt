package com.example.androidkotlintaskd.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface DrinkDBDao {

    @Insert
    fun insert(drinksDB: List<DrinkDB>)

    @Insert
    fun insert(drinkDB: DrinkDB)

    @Query("DELETE FROM DrinkDB")
    fun clearTable()

    @Query("SELECT * FROM DrinkDB")
    fun selectDrink(): List<DrinkDB>
}