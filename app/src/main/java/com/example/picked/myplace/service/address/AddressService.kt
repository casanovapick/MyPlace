package com.example.picked.myplace.service.address

import android.location.Address
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable


interface AddressService {
    fun getAddress(latLng: LatLng): Observable<Address>
}