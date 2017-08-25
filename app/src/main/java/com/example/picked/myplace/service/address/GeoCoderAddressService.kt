package com.example.picked.myplace.service.address

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import java.util.*


class GeoCoderAddressService(context: Context) : AddressService {

    private val geoCoder: Geocoder = Geocoder(context, Locale.getDefault())

    override fun getAddress(latLng: LatLng): Observable<Address> {
        return Observable.fromCallable {
            val addressList = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            addressList[0]
        }
    }

}