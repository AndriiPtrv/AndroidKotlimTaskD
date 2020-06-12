package com.example.androidkotlintaskd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidkotlintaskd.App

import com.example.androidkotlintaskd.R
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.db.model.InfoDrinkDB
import com.example.androidkotlintaskd.network.ApiService
import com.example.androidkotlintaskd.network.model.Drink
import com.example.androidkotlintaskd.network.model.InfoDrink
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_info.*
import kotlin.coroutines.coroutineContext

class InfoFragment(private val id: String) : Fragment() {

    private var disposable: Disposable? = null
    private val listInfoFromServer = mutableListOf<InfoDrink>()
    private val listInfoDB = mutableListOf<InfoDrinkDB>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dao = (activity?.application as App).db.getDrinkDBDao()

        disposable = dao.selectInfoDrinkById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                info_title.text = it.strDrink
                info_category.text = it.strCategory
                info_alcoholic.text = it.strAlcoholic
                info_glass.text = it.strGlass
                info_instructions.text = it.strInstructions
                Picasso
                    .with(activity)
                    .load(it.strDrinkThumb)
                    .into(info_photo)
            }, { it -> showError(it) })


        disposable = ApiService.getDrinkInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                listInfoFromServer.addAll(it.infoDrinks)
                listInfoFromServer.forEach {
                    dao.insertInfoDrink(
                        InfoDrinkDB(
                            0,
                            it.idDrink,
                            it.strDrink,
                            it.strCategory,
                            it.strAlcoholic,
                            it.strGlass,
                            it.strInstructions,
                            it.strDrinkThumb
                        )
                    )
                }
            }, { it -> showError(it) })
    }

    private fun showError(t: Throwable) {
        Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorKEKW(t: Throwable) {
        Toast.makeText(activity, "ERROR (KEKW", Toast.LENGTH_SHORT).show()
    }
}