package com.example.streetshoot

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InicioSesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.iniciosesion_layout)

        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val progress = findViewById<CircularProgressIndicator>(R.id.progress)
        val tvGoRegister = findViewById<TextView>(R.id.tvGoRegister)
        val tvForgot = findViewById<TextView>(R.id.tvForgot)

        fun setLoading(loading: Boolean) {
            progress.visibility = if (loading) View.VISIBLE else View.GONE
            btnLogin.isEnabled = !loading
        }

        btnLogin.setOnClickListener {
            tilEmail.error = null
            tilPassword.error = null

            val email = etEmail.text?.toString()?.trim().orEmpty()
            val password = etPassword.text?.toString().orEmpty()

            var valid = true

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Email no válido"
                valid = false
            }

            if (password.length < 4) {
                tilPassword.error = "Contraseña demasiado corta"
                valid = false
            }

            if (!valid) return@setOnClickListener

            setLoading(true)

            //Login funcional provisional
            btnLogin.postDelayed({
                setLoading(false)
                startActivity(Intent(this, ZoneSelectActivity::class.java))
                finish()
            }, 600)
        }

        tvGoRegister.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }

        tvForgot.setOnClickListener {
            Toast.makeText(this, "Función pendiente", Toast.LENGTH_SHORT).show()
        }
    }
}
