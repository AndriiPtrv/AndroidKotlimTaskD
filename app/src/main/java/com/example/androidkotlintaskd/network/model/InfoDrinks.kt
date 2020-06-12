package com.example.androidkotlintaskd.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoDrinks(
    @SerializedName("drinks")
    @Expose
    val infoDrinks: List<InfoDrink>
)