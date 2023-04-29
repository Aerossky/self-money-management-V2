package com.example.myapplication

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDeleteUserBinding


class DeleteUserActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityDeleteUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityDeleteUserBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        kembali()
        batal()
        hapus()
    }

    fun batal() {
        viewBind.batalDU.setOnClickListener() {
            finish()
        }
    }

    fun kembali() {
        viewBind.backDU.setOnClickListener {
            finish()
        }
    }

    fun hapus() {
        viewBind.hapusDU.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            var index = arrayListUser.indexUser
            arrayListUser.users.removeAt(index)
            startActivity(intent)
            finish()
        }


    }


}

