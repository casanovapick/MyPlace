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

    override fun getItem(position: Int): Fragment {
        when (position) {
            NEARBY_POSITION -> {
                return NearbyFragment()
            }
            FAVORITE_POSITION -> {
                return FavoriteFragment()
            }
            else -> {
                return NearbyFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            NEARBY_POSITION -> {
                return context.getString(R.string.myplace_tab_nearby)
            }
            FAVORITE_POSITION -> {
                return context.getString(R.string.myplace_tab_favorite)
            }
            else -> {
                return ""
            }
        }
    }

    override fun getCount(): Int {
        return NUMBER_OF_PAGE;
    }


}