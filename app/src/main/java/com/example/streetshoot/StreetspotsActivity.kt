package com.example.streetshoot

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StreetspotsActivity : AppCompatActivity() {

    data class Spot(val nombre: String, val tipo: String, val rating: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streetspots)

        val prefs = getSharedPreferences("streetshoot_prefs", MODE_PRIVATE)

        val etNombre = findViewById<EditText>(R.id.etSpotNombre)
        val spTipo = findViewById<Spinner>(R.id.spTipo)
        val tvRating = findViewById<TextView>(R.id.tvRating)
        val sbRating = findViewById<SeekBar>(R.id.sbRating)
        val btnAdd = findViewById<Button>(R.id.btnAddSpot)
        val container = findViewById<LinearLayout>(R.id.containerLista)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Spinner tipos coherentes con StreetShoot
        val tipos = listOf("Cancha", "Parque", "Plaza", "Pista interior", "Otro")
        spTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        // Rating live
        if (sbRating.progress == 0) sbRating.progress = 3
        tvRating.text = "Nota: ${sbRating.progress} ⭐"
        sbRating.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tvRating.text = "Nota: $progress ⭐"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        fun encode(list: List<Spot>): String =
            list.joinToString("||") { "${it.nombre}##${it.tipo}##${it.rating}" }

        fun decode(raw: String): MutableList<Spot> {
            if (raw.isBlank()) return mutableListOf()
            return raw.split("||").mapNotNull { item ->
                val parts = item.split("##")
                if (parts.size != 3) null else Spot(parts[0], parts[1], parts[2].toIntOrNull() ?: 0)
            }.toMutableList()
        }

        fun pintarLista(spots: List<Spot>) {
            container.removeAllViews()
            if (spots.isEmpty()) {
                val empty = TextView(this).apply {
                    text = "Aún no hay streetspots. Añade el primero arriba 👆"
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 16f
                    setPadding(8, 8, 8, 8)
                }
                container.addView(empty)
                return
            }

            spots.forEachIndexed { index, s ->
                val row = TextView(this).apply {
                    text = "• ${s.nombre} (${s.tipo})  —  ${s.rating}⭐"
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 18f
                    setPadding(8, 10, 8, 10)
                }
                container.addView(row)

                // separador simple
                if (index != spots.lastIndex) {
                    val sep = View(this).apply {
                        setBackgroundColor(0x55FFFFFF.toInt())
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            2
                        ).apply {
                            topMargin = 6
                            bottomMargin = 6
                        }
                    }
                    container.addView(sep)
                }
            }
        }

        // Cargar lista guardada
        val raw = prefs.getString("spots_list", "") ?: ""
        val spots = decode(raw)
        pintarLista(spots)

        btnAdd.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Pon un nombre al streetspot", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipo = spTipo.selectedItem?.toString() ?: "Otro"
            val rating = sbRating.progress.coerceIn(0, 5)

            spots.add(0, Spot(nombre, tipo, rating)) // lo nuevo arriba
            prefs.edit().putString("spots_list", encode(spots)).apply()

            etNombre.setText("")
            Toast.makeText(this, "Streetspot guardado ✅", Toast.LENGTH_SHORT).show()
            pintarLista(spots)
        }

        btnVolver.setOnClickListener { finish() }
    }
}
