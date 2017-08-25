package com.example.picked.myplace.repository

import com.example.picked.myplace.entity.place.PlaceInfo
import io.realm.Realm


class FavoritePlaceRepository : PlaceRepository {


    override fun addPlace(placeInfo: PlaceInfo) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(placeInfo)
        realm.commitTransaction()
        realm.close()
    }

    override fun deletePlace(placeId: String) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val placeInfo: PlaceInfo? = realm.where(PlaceInfo::class.java).equalTo("id", placeId).findFirst()
        placeInfo?.deleteFromRealm()
        realm.commitTransaction()
        realm.close()
    }

    override fun getAllPlace(): MutableList<PlaceInfo> {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val realmResults = realm.where(PlaceInfo::class.java).findAll()
        val result = realm.copyFromRealm(realmResults)
        realm.commitTransaction()
        realm.close()
        return result
    }

}