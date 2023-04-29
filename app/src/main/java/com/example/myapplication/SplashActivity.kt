package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myapplication.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        //function splash screen
        showLogin()
        //gg
    }

    private fun showLogin(){
        Handler(Looper.getMainLooper()).postDelayed({
            val die = Intent(this, homeActivity::class.java)
            die.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(die)
        }, 3000)
    }
}