package com.example.picked.myplace.ui.place.nearby

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.picked.myplace.entity.place.PlaceInfo
import com.example.picked.myplace.entity.place.PlaceSearchResponse
import com.example.picked.myplace.geofencing.PlaceGeoFencing
import com.example.picked.myplace.repository.PlaceRepository
import com.example.picked.myplace.service.place.PlaceSearchService
import com.example.picked.myplace.ui.place.PlaceItem
import com.example.picked.myplace.utility.RxSchedulersOverrideRule
import io.reactivex.Observable
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class NearbyPresenterTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val rxRule = RxSchedulersOverrideRule()

    lateinit var presenter: NearbyPresenter

    @Mock lateinit var view: NearbyContract.View

    @Mock lateinit var placeSearchService: PlaceSearchService

    @Mock lateinit var viewModel: NearbyViewModel

    @Mock lateinit var placeRepository: PlaceRepository

    @Mock lateinit var placeGeoFencing: PlaceGeoFencing

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NearbyPresenter(view, placeSearchService, viewModel, placeRepository, placeGeoFencing)
    }

    @Test
    fun changeFavorite_placeIsFavorite() {
        val placeId = "test"
        val placeInfo = PlaceInfo(placeId = placeId)
        val placeItem = PlaceItem(placeInfo, true)
        viewModel.placeItem.add(placeItem)
        presenter.changeFavorite(0)
        verify(placeRepository).deletePlace(placeId)
        verify(placeGeoFencing).removePlace(placeInfo)
    }

    @Test
    fun changeFavorite_placeIsNotFavorite() {
        val placeId = "test"
        val placeInfo = PlaceInfo(placeId = placeId)
        val placeItem = PlaceItem(placeInfo, false)
        viewModel.placeItem.add(placeItem)
        presenter.changeFavorite(0)
        verify(placeRepository).addPlace(placeInfo)
        verify(placeGeoFencing).addPlace(placeInfo)
    }

    @Test
    fun selectedLocation() {
        val id = "Test"
        val placeSearchResponse = PlaceSearchResponse(results = mutableListOf(PlaceInfo(id = id)), status = "OK")
        val observable = Observable.just(placeSearchResponse)
        whenever(placeRepository.getAllPlace()).thenReturn(mutableListOf(PlaceInfo(id = id)))
        whenever(placeSearchService.getNearbyPlace(ArgumentMatchers.any())).thenReturn(observable)
        val lat = 9.0
        val lng = 9.0
        presenter.selectedLocation(lat, lng)
        verify(view).updateList()
    }

    @Test
    fun refreshFavorite() {
        val id = "Test"
        whenever(placeRepository.getAllPlace()).thenReturn(mutableListOf(PlaceInfo(id = id)))
        val placeInfo = PlaceInfo(id = id)
        viewModel.placeItem.add(PlaceItem(placeInfo, false))
        presenter.refreshFavorite()
        Assert.assertTrue(viewModel.placeItem[0].isFavorite)
    }

}