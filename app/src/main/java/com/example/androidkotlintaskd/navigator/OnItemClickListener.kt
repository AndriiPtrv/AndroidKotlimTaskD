package com.example.androidkotlintaskd.navigator

import com.example.androidkotlintaskd.db.model.DrinkDB

interface OnItemClickListener {
    fun onItemClick(drink: DrinkDB)
}