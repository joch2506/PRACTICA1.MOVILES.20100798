package com.example.practica1moviles20100798

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias de los botones
        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnActividad = findViewById<Button>(R.id.btnActividad)
        val btnCatalogoAutos = findViewById<Button>(R.id.btnCatalogoAutos)

        // Navegación hacia cada pantalla
        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraAguaActivity::class.java)
            startActivity(intent)
        }

        btnActividad.setOnClickListener {
            val intent = Intent(this, ActividadFisicaActivity::class.java)
            startActivity(intent)
        }

        btnCatalogoAutos.setOnClickListener {
            val intent = Intent(this, CatalogoAutosActivity::class.java)
            startActivity(intent)
        }
    }
}

