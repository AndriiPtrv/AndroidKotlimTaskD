package com.example.androidkotlintaskd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlintaskd.R
import com.example.androidkotlintaskd.db.model.DrinkDB
import com.example.androidkotlintaskd.navigator.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class DrinkAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<DrinkAdapter.DrinkHolder>() {

    private val list = mutableListOf<DrinkDB>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
        return DrinkHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        val drink: DrinkDB = list[position]
        holder.bind(drink)
    }

    fun update(listServer: List<DrinkDB>){
        list.clear()
        list.addAll(listServer)
        notifyDataSetChanged()
    }

    class DrinkHolder(itemView: View, var listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: DrinkDB){
            Picasso.
                    with(itemView.context).
                    load(drink.strDrinkThumb).
                    into(itemView.photo)
            itemView.title.text = drink.strDrink
            itemView.card_item.setOnClickListener {
                listener.onItemClick(drink)
            }
        }
    }
}