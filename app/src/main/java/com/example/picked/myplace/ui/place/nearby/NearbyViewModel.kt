package com.example.picked.myplace.ui.place.nearby

import android.arch.lifecycle.ViewModel
import com.example.picked.myplace.ui.place.PlaceItem


class NearbyViewModel : ViewModel() {
    val placeItem: MutableList<PlaceItem> = mutableListOf()
}