package com.example.picked.myplace.geofencing

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.picked.myplace.entity.place.PlaceInfo
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices


class MyPlaceGeoFencing(val context: Context) : PlaceGeoFencing {

    private val geoFencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)

    override fun addPlace(placeInfo: PlaceInfo) {
        val intent = Intent(context, GeofenceTransitionsIntentService::class.java)
        intent.putExtra("name", placeInfo.name)
        val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT)
        placeInfo.geometry?.placeLocation?.lat?.let { lat ->
            placeInfo.geometry?.placeLocation?.lng?.let { lng ->
                val geofence = Geofence.Builder()
                        .setRequestId(placeInfo.id)
                        .setCircularRegion(lat, lng, 100.00f)
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER).build()
                val geofencingRequest = GeofencingRequest.Builder()
                        .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                        .addGeofence(geofence).build()
                geoFencingClient.addGeofences(geofencingRequest, pendingIntent)
            }
        }

    }

    override fun removePlace(placeInfo: PlaceInfo) {
        geoFencingClient.removeGeofences(mutableListOf(placeInfo.id))
    }

}