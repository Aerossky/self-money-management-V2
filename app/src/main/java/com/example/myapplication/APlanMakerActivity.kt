package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAplanMakerBinding

class APlanMakerActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityAplanMakerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAplanMakerBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}