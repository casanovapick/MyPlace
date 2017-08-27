package com.example.picked.myplace.ui.myplace

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.picked.myplace.R
import com.example.picked.myplace.ui.place.favorite.FavoriteFragment
import com.example.picked.myplace.ui.place.nearby.NearbyFragment

class MyPlacePagerAdapter(val context: Context, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private val NUMBER_OF_PAGE = 2
    private val NEARBY_POSITION = 0
    private val FAVORITE_POSITION = 1

    override fun getItem(position: Int): Fragment = when (position) {
        NEARBY_POSITION -> {
            NearbyFragment()
        }
        FAVORITE_POSITION -> {
            FavoriteFragment()
        }
        else -> {
            NearbyFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        NEARBY_POSITION -> {
            context.getString(R.string.myplace_tab_nearby)
        }
        FAVORITE_POSITION -> {
            context.getString(R.string.myplace_tab_favorite)
        }
        else -> {
            ""
        }
    }

    override fun getCount(): Int = NUMBER_OF_PAGE


}