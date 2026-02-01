package com.example.streetshoot

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NotificacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        findViewById<Button>(R.id.btnVolver).setOnClickListener { finish() }
    }
}
