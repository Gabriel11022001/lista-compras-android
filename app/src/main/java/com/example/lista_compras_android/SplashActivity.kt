package com.example.lista_compras_android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.redirecionarHome()
    }

    private fun redirecionarHome() {
        Handler().postDelayed({
            val intentRedirecionarHome = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentRedirecionarHome)
            finish()
        }, 2000)
    }

}