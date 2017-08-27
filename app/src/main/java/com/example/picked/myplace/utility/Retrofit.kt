package com.example.picked.myplace.utility

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


fun <T> createApiService(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(clazz)