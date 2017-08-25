package com.example.picked.myplace.ui.place.nearby


import android.app.Activity.RESULT_OK
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.picked.myplace.R
import com.example.picked.myplace.repository.FavoritePlaceRepository
import com.example.picked.myplace.service.place.PlaceSearchService
import com.example.picked.myplace.ui.place.PlaceListAdapter
import com.example.picked.myplace.ui.selectlocation.SelectLocationActivity
import com.example.picked.myplace.utility.createApiService
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_nearby.*


class NearbyFragment : LifecycleFragment(), NearbyContract.View {

    private val SELECT_LOCATION_REQUEST_CODE = 9000
    var presenter: NearbyPresenter? = null
    private lateinit var adapter: PlaceListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?
                              , savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_nearby, container, false)

    lateinit var viewModel: NearbyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        floatingButton.setOnClickListener {
            startActivityForResult(Intent(context, SelectLocationActivity::class.java), SELECT_LOCATION_REQUEST_CODE)
        }
        viewModel = ViewModelProviders.of(this).get(NearbyViewModel::class.java)
        presenter = NearbyPresenter(this, createApiService(PlaceSearchService::class.java)
                , viewModel, FavoritePlaceRepository())
        lifecycle.addObserver(presenter)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = PlaceListAdapter(viewModel.placeItem, {
            presenter?.changeFavorite(it)
        })
        nearbyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        nearbyRecyclerView.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            presenter?.refreshFavorite()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == SELECT_LOCATION_REQUEST_CODE) {
            data?.let {
                val latLng: LatLng = it.extras.getParcelable(LatLng::class.java.name)
                presenter?.selectedLocation(latLng.latitude, latLng.longitude)
            }
        }
    }
}
