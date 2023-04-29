package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivitySplashBinding
import kotlin.math.log

class homeActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        pindahRegister()
        pindahLogin()
    }

    private fun pindahRegister(){
        viewBind.gotoregisterButton.setOnClickListener{
            val myIntent=Intent(this, SignUpActivity::class.java).apply {

            }
            myIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)

        }
    }
    private fun pindahLogin(){
        viewBind.gotologinButton.setOnClickListener{
            val myIntent=Intent(this, loginActivity::class.java).apply {

            }
            myIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)

        }
    }
}