package com.example.picked.myplace.repository

import com.example.picked.myplace.entity.place.PlaceInfo


interface PlaceRepository {
    fun addPlace(placeInfo: PlaceInfo)
    fun deletePlace(placeId: String)
    fun getAllPlace(): MutableList<PlaceInfo>
}