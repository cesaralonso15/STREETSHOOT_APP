package com.example.streetshoot

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

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_layout)

        //Inputs
        val tilNick = findViewById<TextInputLayout>(R.id.tilNick)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val tilPassword2 = findViewById<TextInputLayout>(R.id.tilPassword2)

        val etNick = findViewById<TextInputEditText>(R.id.etNick)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etPassword2 = findViewById<TextInputEditText>(R.id.etPassword2)

        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
        val progress = findViewById<CircularProgressIndicator>(R.id.progress)
        val tvGoLogin = findViewById<TextView>(R.id.tvGoLogin)

        fun setLoading(loading: Boolean) {
            progress.visibility = if (loading) View.VISIBLE else View.GONE
            btnRegister.isEnabled = !loading
        }

        btnRegister.setOnClickListener {

            // Limpiar errores
            tilNick.error = null
            tilEmail.error = null
            tilPassword.error = null
            tilPassword2.error = null

            val nick = etNick.text?.toString()?.trim().orEmpty()
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass1 = etPassword.text?.toString().orEmpty()
            val pass2 = etPassword2.text?.toString().orEmpty()

            var valid = true

            //Nick
            if (nick.isEmpty()) {
                tilNick.error = "El nick es obligatorio"
                valid = false
            } else if (nick.length < 3) {
                tilNick.error = "El nick debe tener al menos 3 caracteres"
                valid = false
            }

            // Email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Email no válido"
                valid = false
            }

            // Password
            if (pass1.length < 6) {
                tilPassword.error = "La contraseña debe tener al menos 6 caracteres"
                valid = false
            }

            // Confirmación password
            if (pass1 != pass2) {
                tilPassword2.error = "Las contraseñas no coinciden"
                valid = false
            }

            if (!valid) return@setOnClickListener

            // Simulación de registro correcto (Sprint actual)
            setLoading(true)
            btnRegister.postDelayed({
                setLoading(false)
                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                finish() // vuelve al login
            }, 800)
        }

        tvGoLogin.setOnClickListener {
            finish()
        }
    }
}
