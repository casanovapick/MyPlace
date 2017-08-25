package com.example.picked.myplace.ui.place.nearby

import com.example.picked.myplace.mvp.MvpView


interface NearbyContract {
    interface View : MvpView {
        fun updateList()
    }

    interface Action {
        fun selectedLocation(lat: Double, lng: Double)
        fun changeFavorite(position: Int)
        fun refreshFavorite()
    }
}