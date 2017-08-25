package com.example.picked.myplace.ui.place.favorite

import com.example.picked.myplace.mvp.MvpView


interface FavoriteContract {

    interface View : MvpView {
        fun updateList()
    }

    interface Action {
        fun reloadFavorite()
        fun unFavorite(position: Int)
    }
}