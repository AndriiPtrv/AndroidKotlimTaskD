package com.example.androidkotlintaskd.navigator

import com.example.androidkotlintaskd.db.model.DrinkDB

interface FragmentNavigator {
    fun showInfo(drink: DrinkDB)
}