package com.example.picked.myplace.ui.selectlocation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.location.Address
import com.example.picked.myplace.service.address.AddressService
import com.example.picked.myplace.utility.RxSchedulersOverrideRule
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*
import org.mockito.Mockito.`when` as whenever

class SelectLocationPresenterTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val rxRule = RxSchedulersOverrideRule()

    lateinit var presenter: SelectLocationPresenter

    @Mock lateinit var view: SelectLocationContract.View

    @Mock lateinit var addressService: AddressService

    var viewModel: SelectLocationViewModel = SelectLocationViewModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SelectLocationPresenter(view, addressService, viewModel)
    }

    @Test
    fun selectAddress_addressEmpty_doNothing() {
        val latLng = LatLng(9.0, 9.0)
        viewModel.latLng = latLng
        viewModel.displayAddress.value = ""
        presenter.selectAddress()
        verify(view, never()).sendSelectLocation(latLng)
    }

    @Test
    fun selectAddress_hasEmpty_sendSelectLocation() {
        val latLng = LatLng(9.0, 9.0)
        viewModel.latLng = latLng
        viewModel.displayAddress.value = "ABCD"
        presenter.selectAddress()
        verify(view).sendSelectLocation(latLng)
    }


    @Test
    fun movePin() {
        val latLng = LatLng(9.0, 9.0)
        val address = Address(Locale.getDefault())
        val addressLine0 = "AAAAAAAA"
        address.setAddressLine(0, addressLine0)
        whenever(addressService.getAddress(ArgumentMatchers.any())).thenReturn(Observable.just(address))
        presenter.movePin(latLng)
        assertEquals(addressLine0, viewModel.displayAddress.value)
    }

}
