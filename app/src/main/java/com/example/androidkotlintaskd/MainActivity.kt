package com.example.androidkotlintaskd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.fragments.InfoFragment
import com.example.androidkotlintaskd.fragments.ListFragment
import com.example.androidkotlintaskd.navigator.FragmentNavigator
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity(), FragmentNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListFragment())
            .commit()
    }

    override fun showInfo(drink: DrinkDB) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, InfoFragment(drink.idDrink))
            .addToBackStack(null)
            .commit()
    }
}
