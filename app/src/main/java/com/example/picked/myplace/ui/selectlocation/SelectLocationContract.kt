package com.example.picked.myplace.ui.selectlocation

import com.example.picked.myplace.mvp.MvpView
import com.google.android.gms.maps.model.LatLng


interface SelectLocationContract {
    interface View : MvpView {
        fun sendSelectLocation(latLng: LatLng)
    }

    interface Action {
        fun movePin(latLng: LatLng)
        fun selectAddress()
    }
}