package com.example.streetshoot  // <-- cámbialo si tu package es otro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class HubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        val cardZonas = findViewById<MaterialCardView>(R.id.cardZonas)
        val cardStreetspots = findViewById<MaterialCardView>(R.id.cardStreetspots)
        val cardRetos = findViewById<MaterialCardView>(R.id.cardRetos)
        val cardNotifs = findViewById<MaterialCardView>(R.id.cardNotifs)
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)

        cardZonas.setOnClickListener {
            startActivity(Intent(this, ZonasActivity::class.java))
        }

        cardStreetspots.setOnClickListener {
            startActivity(Intent(this, StreetspotsActivity::class.java))
        }

        cardRetos.setOnClickListener {
            startActivity(Intent(this, RetosActivity::class.java))
        }

        cardNotifs.setOnClickListener {
            startActivity(Intent(this, NotificacionesActivity::class.java))
        }

        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PersonalizarPerfilActivity::class.java))
        }
    }
}
