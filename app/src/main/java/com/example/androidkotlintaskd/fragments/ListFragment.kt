package com.example.androidkotlintaskd.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidkotlintaskd.App

import com.example.androidkotlintaskd.R
import com.example.androidkotlintaskd.adapter.DrinkAdapter
import com.example.androidkotlintaskd.db.DrinkDB
import com.example.androidkotlintaskd.network.ApiService
import com.example.androidkotlintaskd.network.model.Drink
import com.example.androidkotlintaskd.network.model.Drinks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val drinkAdapter = DrinkAdapter()
    private val dao = (activity as App).db.getDrinkDBDao() // ERROR

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        list_of_drinks.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = drinkAdapter
        }

        val dis = ApiService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showListDrinks, this:: showError)
    }

    private fun showListDrinks(drinks: Drinks) {
       drinks.drinks.forEachIndexed { _, drink ->
           dao.insert(DrinkDB(drink.idDrink.toLong(), drink.strDrink, drink.strDrinkThumb))
       }
        val list = dao.selectDrink()
        drinkAdapter.update(list)
    }

    private fun showError(t: Throwable) {
        Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
    }
}
