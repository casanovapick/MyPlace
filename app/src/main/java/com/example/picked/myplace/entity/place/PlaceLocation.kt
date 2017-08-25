package com.example.picked.myplace.entity.place

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class PlaceLocation(

        @field:SerializedName("lng")
        var lng: Double? = null,

        @field:SerializedName("lat")
        var lat: Double? = null
) : RealmObject()