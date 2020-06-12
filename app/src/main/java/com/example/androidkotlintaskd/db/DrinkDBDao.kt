package com.example.androidkotlintaskd.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface DrinkDBDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(drinksDB: List<DrinkDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(drinkDB: DrinkDB)

    @Query("DELETE FROM DrinkDB")
    fun clearTable()

    @Query("SELECT * FROM DrinkDB")
    fun selectDrink(): Flowable<List<DrinkDB>>
}