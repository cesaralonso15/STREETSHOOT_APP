package com.example.streetshoot

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PersonalizarPerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizar_perfil)

        val prefs = getSharedPreferences("streetshoot_prefs", MODE_PRIVATE)

        val tvNickPreview = findViewById<TextView>(R.id.tvNickPreview)
        val etNick = findViewById<EditText>(R.id.etNick)
        val rgPlaystyle = findViewById<RadioGroup>(R.id.rgPlaystyle)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarPerfil)
        val btnVolver = findViewById<Button>(R.id.btnVolverPerfil)

        // Cargar
        val nickSaved = prefs.getString("nick", "HECTOR") ?: "HECTOR"
        val styleSaved = prefs.getString("playstyle", "Shooter") ?: "Shooter"

        tvNickPreview.text = "Jugador: ${nickSaved.uppercase()}"
        etNick.setText(nickSaved)

        when (styleSaved) {
            "Playmaker" -> rgPlaystyle.check(R.id.rbPlaymaker)
            "Defender" -> rgPlaystyle.check(R.id.rbDefender)
            else -> rgPlaystyle.check(R.id.rbShooter)
        }

        btnGuardar.setOnClickListener {
            val nick = etNick.text.toString().trim().ifEmpty { "HECTOR" }
            val style = when (rgPlaystyle.checkedRadioButtonId) {
                R.id.rbPlaymaker -> "Playmaker"
                R.id.rbDefender -> "Defender"
                else -> "Shooter"
            }

            prefs.edit()
                .putString("nick", nick)
                .putString("playstyle", style)
                .apply()

            tvNickPreview.text = "Jugador: ${nick.uppercase()}"
            Toast.makeText(this, "Perfil guardado ✅", Toast.LENGTH_SHORT).show()
        }

        btnVolver.setOnClickListener { finish() }
    }
}
