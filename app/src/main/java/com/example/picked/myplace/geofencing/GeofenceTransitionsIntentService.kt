package com.example.picked.myplace.geofencing


import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.picked.myplace.R
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent


class GeofenceTransitionsIntentService : IntentService(GeofenceTransitionsIntentService::class.java.name) {

    override fun onHandleIntent(intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (!geofencingEvent.hasError()) {
            val geofenceTransition = geofencingEvent.geofenceTransition
            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                val placeName = intent?.getStringExtra("name")
                sendNotification(placeName)
            }
        }
    }

    private fun sendNotification(placeName: String?) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        val notification = NotificationCompat.Builder(applicationContext)
                .setContentTitle(placeName)
                .setContentText(getString(R.string.notification_arrive_message) + placeName).build()
        notificationManager?.notify(0, notification)
    }
}