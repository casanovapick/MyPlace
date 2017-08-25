package com.example.picked.myplace.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.picked.myplace.R
import com.example.picked.myplace.ui.myplace.MyPlaceActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 3000L
    private val runnable = Runnable { openMyPlaceActivity() }
    private val handler = Handler()

    private fun openMyPlaceActivity() {
        startActivity(Intent(this, MyPlaceActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, SPLASH_DELAY)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}
