package com.example.androidkotlintaskd

import android.app.Application
import com.example.androidkotlintaskd.db.DrinkDataBase

class App: Application() {

    lateinit var db: DrinkDataBase

    override fun onCreate() {
        super.onCreate()
        db = DrinkDataBase.getInstance(this)
    }
}