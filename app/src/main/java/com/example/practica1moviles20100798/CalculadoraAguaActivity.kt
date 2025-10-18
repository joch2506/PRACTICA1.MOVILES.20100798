package com.example.practica1moviles20100798

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class CalculadoraAguaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_agua)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val rgGenero = findViewById<RadioGroup>(R.id.rgGenero)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val pesoTexto = etPeso.text.toString().trim()

            // Validar nombre
            if (nombre.isEmpty()) {
                mostrarMensaje("Por favor ingresa el nombre")
                return@setOnClickListener
            }

            // Validar peso
            if (pesoTexto.isEmpty()) {
                mostrarMensaje("Por favor ingresa el peso")
                return@setOnClickListener
            }

            val peso = pesoTexto.toDoubleOrNull()
            if (peso == null || peso < 5 || peso > 200) {
                mostrarMensaje("El peso debe ser un número entre 5 y 200 kg")
                return@setOnClickListener
            }

            // Validar género
            val generoSeleccionado = when (rgGenero.checkedRadioButtonId) {
                R.id.rbMasculino -> "Masculino"
                R.id.rbFemenino -> "Femenino"
                R.id.rbSinEspecificar -> "Sin especificar"
                else -> ""
            }

            if (generoSeleccionado.isEmpty()) {
                mostrarMensaje("Selecciona un género")
                return@setOnClickListener
            }

            // Factor por género
            val factor = when (generoSeleccionado) {
                "Masculino" -> 1.02
                "Femenino" -> 1.01
                else -> 1.00
            }

            // Cálculo
            val litros = peso * 0.035 * factor
            val resultado = String.format("%.2f", litros)

            tvResultado.text = "$nombre debe beber aproximadamente $resultado litros de agua al día"
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
