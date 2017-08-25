package com.example.picked.myplace.entity.place

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class PhotosItem(

        @field:SerializedName("photo_reference")
        var photoReference: String? = null,

        @field:SerializedName("width")
        var width: Int? = null
) : RealmObject()