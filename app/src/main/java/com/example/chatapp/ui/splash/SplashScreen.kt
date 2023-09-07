package com.example.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.chatapp.R
import com.example.chatapp.ui.login.LoginActivity
import com.example.chatapp.ui.register.RegisterActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
                                          startRegisterActivity()
        },2000)
    }

    private fun startRegisterActivity() {
        val intent = Intent(this@SplashScreen,RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}