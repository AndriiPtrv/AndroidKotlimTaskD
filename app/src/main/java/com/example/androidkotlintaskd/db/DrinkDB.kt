package com.example.androidkotlintaskd.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkDB (
    @PrimaryKey
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)