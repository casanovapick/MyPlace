package com.example.picked.myplace.entity.place

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PlaceInfo(

        @field:SerializedName("reference")
        var reference: String? = null,

        @field:SerializedName("icon")
        var icon: String? = null,

        @field:SerializedName("name")
        var name: String? = null,

        @field:SerializedName("geometry")
        var geometry: Geometry? = null,

        @field:SerializedName("vicinity")
        var vicinity: String? = null,
        @PrimaryKey
        @field:SerializedName("id")
        var id: String? = null,

        @field:SerializedName("photos")
        var photos: RealmList<PhotosItem>? = RealmList(),

        @field:SerializedName("place_id")
        var placeId: String? = null
) : RealmObject()