package com.example.picked.myplace.ui.selectlocation

import android.util.Log
import com.example.picked.myplace.mvp.BasePresenter
import com.example.picked.myplace.service.address.AddressService
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class SelectLocationPresenter(view: SelectLocationContract.View?
                              , val addressService: AddressService
                              , val viewModel: SelectLocationViewModel)
    : BasePresenter<SelectLocationContract.View>(view), SelectLocationContract.Action {

    override fun selectAddress() {
        viewModel.displayAddress.value?.let {
            if (it.isNotBlank()) {
                viewModel.latLng?.let {
                    view?.sendSelectLocation(it)
                }
            }
        }
    }

    override fun movePin(latLng: LatLng) {
        viewModel.latLng = latLng
        addressService.getAddress(latLng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            viewModel.displayAddress.value = it.getAddressLine(0)
                        },
                        onError = {
                            Log.e("error", "" + it)
                        }
                ).addTo(compositeDisposable)
    }

}