package com.example.androidkotlintaskd.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String
)