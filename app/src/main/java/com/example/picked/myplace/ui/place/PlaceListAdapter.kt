package com.example.picked.myplace.ui.place

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.picked.myplace.BR
import com.example.picked.myplace.R
import com.example.picked.myplace.binding.BindingHolder
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceListAdapter(var placeItem: MutableList<PlaceItem>, val favoriteClick: ((Int) -> Unit)) : RecyclerView.Adapter<PlaceListAdapter.PlaceItemViewHolder>() {


    override fun onBindViewHolder(holder: PlaceItemViewHolder?, position: Int) {
        placeItem[position].let { holder?.bind(it, position) }
    }

    override fun getItemCount(): Int = placeItem.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaceItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_place, parent, false)
        return PlaceItemViewHolder(view, favoriteClick)
    }

    class PlaceItemViewHolder(itemView: View, val favoriteClick: ((Int) -> Unit)) : BindingHolder(itemView) {
        fun bind(placeItem: PlaceItem, position: Int) {
            binder.setVariable(BR.placeItem, placeItem)
            binder.executePendingBindings()
            itemView.favoriteButton.setOnClickListener {
                favoriteClick(position)
            }
        }
    }
}