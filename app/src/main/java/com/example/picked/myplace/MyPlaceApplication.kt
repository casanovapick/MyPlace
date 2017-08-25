package com.example.picked.myplace

import android.app.Application
import io.realm.Realm


class MyPlaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
