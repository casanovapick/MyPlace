package com.example.picked.myplace.utility

import android.location.Location


fun Location.isGoodLocation(): Boolean {
    val notZero = latitude != 0.0 || longitude != 0.0
    return accuracy < 90 && notZero
}