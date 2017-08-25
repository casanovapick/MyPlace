package com.example.picked.myplace.ui.selectlocation

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.example.picked.myplace.R
import com.example.picked.myplace.service.address.GeoCoderAddressService
import com.example.picked.myplace.utility.isGoodLocation
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_select_location.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class SelectLocationActivity : LifecycleActivity(), OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks, LocationListener, SelectLocationContract.View {

    lateinit var googleApiClient: GoogleApiClient
    lateinit var map: GoogleMap
    lateinit var viewModel: SelectLocationViewModel
    lateinit var presenter: SelectLocationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)
        setupView()
        presenter = SelectLocationPresenter(this, GeoCoderAddressService(this), viewModel)
        lifecycle.addObserver(presenter)
        setupMapView()
    }

    private fun setupView() {
        viewModel = ViewModelProviders.of(this).get(SelectLocationViewModel::class.java)
        viewModel.displayAddress.observe(this, Observer {
            addressTextView.text = it
        })
        addressTextView.setOnClickListener {
            presenter.selectAddress()
        }
    }

    private fun setupMapView() {
        val supportMapFragment = SupportMapFragment()
        supportFragmentManager.beginTransaction().replace(R.id.mapLayout, supportMapFragment).commit()
        supportMapFragment.getMapAsync(this)
    }


    override fun onMapReady(map: GoogleMap?) {
        this.map = map!!
        connectGoogleApi()
        map.setOnCameraIdleListener {
            presenter.movePin(map.cameraPosition.target)
        }
    }

    private fun connectGoogleApi() {
        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build()
        googleApiClient.connect()
    }

    override fun onConnectionSuspended(p0: Int) {

    }


    override fun onConnected(p0: Bundle?) {
        if (viewModel.latLng == null) {
            SelectLocationActivityPermissionsDispatcher.requestLocationWithCheck(this)
        } else {
            moveCamera(viewModel.latLng!!)
        }
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun requestLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.fastestInterval = 100
        locationRequest.interval = 100
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this)
        onLocationChanged(lastLocation)
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            if (it.isGoodLocation()) {
                moveCamera(LatLng(it.latitude, it.longitude))
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
            }
        }
    }

    private fun moveCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .zoom(17f)
                .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        SelectLocationActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults)
    }

    override fun sendSelectLocation(latLng: LatLng) {
        val intent = Intent()
        intent.putExtra(LatLng::class.java.name, latLng)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
