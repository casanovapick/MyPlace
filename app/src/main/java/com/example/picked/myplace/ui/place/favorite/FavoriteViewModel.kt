package com.example.picked.myplace.ui.place.favorite

import android.arch.lifecycle.ViewModel
import com.example.picked.myplace.ui.place.PlaceItem


class FavoriteViewModel : ViewModel() {
    val placeItemList = mutableListOf<PlaceItem>()
}