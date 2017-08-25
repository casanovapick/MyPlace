package com.example.picked.myplace.entity.place

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Geometry(
        @field:SerializedName("Location")
        var placeLocation: PlaceLocation? = null
) : RealmObject()