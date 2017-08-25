package com.example.picked.myplace.service.place

import com.example.picked.myplace.BuildConfig
import com.example.picked.myplace.entity.place.PlaceSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceSearchService {
    @GET("place/nearbysearch/json?radius=100&key=${BuildConfig.GOOGLE_API_KEY}")
    fun getNearbyPlace(@Query("location") locationString: String): Observable<PlaceSearchResponse>
}