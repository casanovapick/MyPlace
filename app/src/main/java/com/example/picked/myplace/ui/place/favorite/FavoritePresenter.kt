package com.example.picked.myplace.ui.place.favorite

import com.example.picked.myplace.geofencing.PlaceGeoFencing
import com.example.picked.myplace.mvp.BasePresenter
import com.example.picked.myplace.repository.PlaceRepository
import com.example.picked.myplace.ui.place.PlaceItem


class FavoritePresenter(view: FavoriteContract.View?
                        , private val placeRepository: PlaceRepository
                        , private val viewModel: FavoriteViewModel, private val placeGeoFencing: PlaceGeoFencing)
    : BasePresenter<FavoriteContract.View>(view), FavoriteContract.Action {

    override fun unFavorite(position: Int) {
        val placeItem = viewModel.placeItemList[position]
        placeItem.placeInfo.id?.let {
            placeRepository.deletePlace(it)
            placeGeoFencing.removePlace(placeItem.placeInfo)
            viewModel.placeItemList.removeAt(position)
        }
        view?.updateList()

    }

    override fun reloadFavorite() {
        viewModel.placeItemList.clear()
        val placeList = placeRepository.getAllPlace()
        placeList.forEach {
            viewModel.placeItemList.add(PlaceItem(it, true))
        }
        view?.updateList()
    }

}