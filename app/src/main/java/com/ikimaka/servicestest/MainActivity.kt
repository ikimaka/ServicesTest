package com.ikimaka.servicestest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ikimaka.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this))
        }
        binding.foregroundService.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

        notificationManager.createNotificationChannel(notificationChannel)

        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .build()


        notificationManager.notify(1, notification)
    }
    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}