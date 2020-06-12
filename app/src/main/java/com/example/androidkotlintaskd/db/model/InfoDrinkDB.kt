package com.example.androidkotlintaskd.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InfoDrinkDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val idDrink: String,
    val strDrink: String,
    val strCategory: String,
    val strAlcoholic: String,
    val strGlass: String,
    val strInstructions: String,
    val strDrinkThumb: String
)