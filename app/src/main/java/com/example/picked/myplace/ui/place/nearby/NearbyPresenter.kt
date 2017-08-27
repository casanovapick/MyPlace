package com.example.picked.myplace.ui.place.nearby

import android.util.Log
import com.example.picked.myplace.geofencing.PlaceGeoFencing
import com.example.picked.myplace.mvp.BasePresenter
import com.example.picked.myplace.repository.PlaceRepository
import com.example.picked.myplace.service.place.PlaceSearchService
import com.example.picked.myplace.ui.place.PlaceItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class NearbyPresenter(view: NearbyContract.View
                      , private val placeSearchService: PlaceSearchService
                      , private val viewModel: NearbyViewModel
                      , private val placeRepository: PlaceRepository
                      , private val placeGeoFencing: PlaceGeoFencing)
    : BasePresenter<NearbyContract.View>(view), NearbyContract.Action {

    override fun changeFavorite(position: Int) {
        val placeListItem = viewModel.placeItem[position]
        placeListItem.isFavorite = !placeListItem.isFavorite
        view?.updateList()
        if (placeListItem.isFavorite) {
            placeRepository.addPlace(placeListItem.placeInfo)
            placeGeoFencing.addPlace(placeListItem.placeInfo)
        } else {
            placeListItem.placeInfo.id?.let {
                placeRepository.deletePlace(it)
                placeGeoFencing.removePlace(placeListItem.placeInfo)
            }
        }
    }

    override fun selectedLocation(lat: Double, lng: Double) {
        placeSearchService.getNearbyPlace("$lat,$lng")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(onNext = {
                    val favoriteIdSet = placeRepository.getAllPlace().map { it.id }.toSet()
                    viewModel.placeItem.clear()
                    val placeList = mutableListOf<PlaceItem>()
                    it.results?.forEach {
                        it?.let { placeInfo ->
                            val favorite = favoriteIdSet.contains(placeInfo.id)
                            placeList.add(PlaceItem(placeInfo, favorite))
                        }
                    }
                    viewModel.placeItem.addAll(placeList)
                    view?.updateList()
                }, onError = {
                    Log.e("error", "" + it)
                })
                .addTo(compositeDisposable)
    }

    override fun refreshFavorite() {
        val favoriteIdSet = placeRepository.getAllPlace().map { it.id }.toSet()
        viewModel.placeItem.forEach {
            val favorite = favoriteIdSet.contains(it.placeInfo.id)
            it.isFavorite = favorite
        }
        view?.updateList()
    }
}