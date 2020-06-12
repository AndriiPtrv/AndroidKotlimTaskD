package com.example.androidkotlintaskd.fragments

import android.content.Context
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
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.navigator.FragmentNavigator
import com.example.androidkotlintaskd.navigator.OnItemClickListener
import com.example.androidkotlintaskd.network.ApiService
import com.example.androidkotlintaskd.network.model.Drink
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), OnItemClickListener {

    private var disposable: Disposable? = null
    private val listFromServer = mutableListOf<Drink>()
    private val listDB = mutableListOf<DrinkDB>()
    private val drinkAdapter = DrinkAdapter(this)
    private var navigator: FragmentNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as FragmentNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dao = (activity?.application as App).db.getDrinkDBDao() // Объявлять тут!!!

        list_of_drinks.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = drinkAdapter
        }

        disposable = dao.selectDrink()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                listDB.addAll(it)
                showListDrinks(listDB)
            },
                { it -> showError(it) })

        disposable = ApiService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                listFromServer.addAll(it.drinks)
                listFromServer.forEach {
                    dao.insertDrinks(
                        DrinkDB(
                            0,
                            it.strDrink,
                            it.strDrinkThumb,
                            it.idDrink
                        )
                    )
                }
            }, { it -> showError(it) })
    }

    private fun showListDrinks(list: MutableList<DrinkDB>) {
        drinkAdapter.update(list)
    }

    override fun onItemClick(drink: DrinkDB) {
        navigator?.showInfo(drink)
    }

    private fun showError(t: Throwable) {
        Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
