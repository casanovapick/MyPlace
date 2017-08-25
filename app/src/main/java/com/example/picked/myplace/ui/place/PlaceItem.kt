package com.example.picked.myplace.ui.place

import com.example.picked.myplace.BuildConfig
import com.example.picked.myplace.entity.place.PlaceInfo


class PlaceItem(val placeInfo: PlaceInfo, var isFavorite: Boolean) {
    val imageUrl: String
        get() {
            var url = ""
            placeInfo.photos?.let {
                if (it.isNotEmpty()) {
                    url = "https://maps.googleapis.com/maps/api/place/photo" +
                            "?maxwidth=400" +
                            "&photoreference=${it[0].photoReference}" +
                            "&key=${BuildConfig.GOOGLE_API_KEY}"
                }
            }
            return url
        }
}