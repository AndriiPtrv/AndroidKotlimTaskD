package com.example.androidkotlintaskd.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.db.model.InfoDrinkDB
import io.reactivex.Flowable

@Dao
interface DrinkDBDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrinks(drinksDB: List<DrinkDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrinks(drinkDB: DrinkDB)

    @Query("DELETE FROM DrinkDB")
    fun clearTableDrinks()

    @Query("SELECT * FROM DrinkDB")
    fun selectDrink(): Flowable<List<DrinkDB>>


    //----------------------------------------------


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInfoDrink(infoDrinkDB: InfoDrinkDB)

    @Query("DELETE FROM InfoDrinkDB")
    fun clearTableInfo()

    @Query("SELECT * FROM InfoDrinkDB WHERE idDrink = :inputID")
    fun selectInfoDrinkById(inputID: String): Flowable<InfoDrinkDB>
}