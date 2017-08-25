package com.example.picked.myplace.entity.place

import com.google.gson.annotations.SerializedName

data class PlaceSearchResponse(

        @field:SerializedName("results")
        val results: MutableList<PlaceInfo?>? = null,

        @field:SerializedName("status")
        val status: String? = null
)