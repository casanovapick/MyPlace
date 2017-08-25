package com.example.picked.myplace.ui.selectlocation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng


class SelectLocationViewModel : ViewModel() {
    var displayAddress: MutableLiveData<String> = MutableLiveData()
    var latLng: LatLng? = null
}