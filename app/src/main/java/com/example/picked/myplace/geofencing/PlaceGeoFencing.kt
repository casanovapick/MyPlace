package com.example.picked.myplace.geofencing

import com.example.picked.myplace.entity.place.PlaceInfo


interface PlaceGeoFencing {
    fun addPlace(placeInfo: PlaceInfo)
    fun removePlace(placeInfo: PlaceInfo)
}