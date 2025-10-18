package com.example.practica1moviles20100798

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActividadFisicaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_fisica)

        val spinnerActividad = findViewById<Spinner>(R.id.spinnerActividad)
        val etDuracion = findViewById<EditText>(R.id.etDuracion)
        val rgIntensidad = findViewById<RadioGroup>(R.id.rgIntensidad)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Lista de actividades
        val actividades = listOf("Seleccione una actividad", "Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, actividades)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerActividad.adapter = adaptador

        btnCalcular.setOnClickListener {
            val actividadSeleccionada = spinnerActividad.selectedItem.toString()
            val duracionTexto = etDuracion.text.toString().trim()

            // Validaciones
            if (actividadSeleccionada == "Seleccione una actividad") {
                mostrarMensaje("Selecciona una actividad")
                return@setOnClickListener
            }

            if (duracionTexto.isEmpty()) {
                mostrarMensaje("Ingresa la duración en minutos")
                return@setOnClickListener
            }

            val duracion = duracionTexto.toIntOrNull()
            if (duracion == null || duracion <= 0) {
                mostrarMensaje("La duración debe ser un número entero positivo")
                return@setOnClickListener
            }

            val intensidadSeleccionada = when (rgIntensidad.checkedRadioButtonId) {
                R.id.rbBaja -> "Baja"
                R.id.rbMedia -> "Media"
                R.id.rbAlta -> "Alta"
                else -> ""
            }

            if (intensidadSeleccionada.isEmpty()) {
                mostrarMensaje("Selecciona una intensidad")
                return@setOnClickListener
            }

            // Calorías por minuto según actividad
            val caloriasPorMin = when (actividadSeleccionada) {
                "Correr" -> 10
                "Caminar" -> 5
                "Nadar" -> 8
                "Ciclismo" -> 7
                "Yoga" -> 4
                else -> 0
            }

            // Factor por intensidad
            val factor = when (intensidadSeleccionada) {
                "Baja" -> 0.8
                "Media" -> 1.0
                "Alta" -> 1.2
                else -> 1.0
            }

            // Cálculo total
            val caloriasQuemadas = caloriasPorMin * duracion * factor
            val resultado = String.format("%.2f", caloriasQuemadas)

            tvResultado.text = "Has quemado aproximadamente $resultado calorías realizando $actividadSeleccionada con intensidad $intensidadSeleccionada."
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
