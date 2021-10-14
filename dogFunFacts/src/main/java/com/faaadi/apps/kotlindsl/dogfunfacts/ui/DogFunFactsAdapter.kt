package com.faaadi.apps.kotlindsl.dogfunfacts.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faaadi.apps.kotlindsl.dogfunfacts.R
import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import kotlinx.android.synthetic.main.item_dogs_fact_layout.view.*


class DogFunFactsAdapter(private val facts: List<DogFactsResModel>) : RecyclerView.Adapter<DogFunFactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dogs_fact_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDogFunFact.text = facts[position]?.fact?:""
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvDogFunFact: TextView = itemView.tvDogFunFact
    }
}