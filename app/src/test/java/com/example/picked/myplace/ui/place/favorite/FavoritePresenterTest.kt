package com.example.picked.myplace.ui.place.favorite

import com.example.picked.myplace.entity.place.PlaceInfo
import com.example.picked.myplace.repository.PlaceRepository
import com.example.picked.myplace.ui.place.PlaceItem
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class FavoritePresenterTest {

    lateinit var presenter: FavoritePresenter


    @Mock lateinit var view: FavoriteContract.View

    @Mock lateinit var repository: PlaceRepository

    private val viewModel: FavoriteViewModel = FavoriteViewModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoritePresenter(view, repository, viewModel)
    }

    @Test
    fun unFavorite() {
        val id = "AAAA"
        viewModel.placeItemList.add(PlaceItem(PlaceInfo(id = id), true))
        presenter.unFavorite(0)
        verify(repository).deletePlace(id)
    }

    @Test
    fun reloadFavorite() {
        val placeList = mutableListOf<PlaceInfo>()
        placeList.add(PlaceInfo(id = "Test"))
        whenever(repository.getAllPlace()).thenReturn(placeList)
        presenter.reloadFavorite()
        verify(view).updateList()
    }

}