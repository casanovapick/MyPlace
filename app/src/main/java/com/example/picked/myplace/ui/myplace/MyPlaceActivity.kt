package com.example.picked.myplace.ui.myplace

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.picked.myplace.R
import kotlinx.android.synthetic.main.activity_my_place.*

class MyPlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_place)
        pager.adapter = MyPlacePagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(pager)
    }

}
