package com.example.myapplication

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityDetailProfilBinding

class DetailProfil : AppCompatActivity() {

    private lateinit var viewBind: ActivityDetailProfilBinding
    private lateinit var user: User
    var indexSekarang = arrayListUser.indexUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityDetailProfilBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        back()
        batal()
        ubahClick()
//        viewBind.showPassword.text = arrayListUser.users.get(indexSekarang).password
    }

    private fun ubahClick() {
        var isCompleted = true
        var passwordVerifikasi = arrayListUser.users.get(indexSekarang).password
        viewBind.simpanDTP.setOnClickListener {

//            verifikasi
            var lama = viewBind.sandiLama.editText?.text!!.toString().trim()
            var baru = viewBind.sandiBaru.editText?.text!!.toString().trim()

//            object
            var nama = arrayListUser.users.get(indexSekarang).nama
            var email = arrayListUser.users.get(indexSekarang).email
            var password = baru

            user = User(nama, email, password)

            //lama
            if (lama?.isEmpty() == true) {
                viewBind.sandiLama.error = "Kolom Belum Terisi"
            } else {
                viewBind.sandiLama.error = ""
            }

            //Password
            if (baru?.isEmpty() == true) {
                viewBind.sandiBaru.error = "Kolom Password Belum Terisi"

            } else {
                if (baru!!.length < 8) {
                    viewBind.sandiBaru.error = "Jumlah password min 8 karakter"

                } else if (!user.password!!.matches(".*[a-z].*".toRegex())) {
                    viewBind.sandiBaru.error = "Password tidak memiliki huruf kecil"

                } else if (!user.password!!.matches(".*[A-Z].*".toRegex())) {
                    viewBind.sandiBaru.error =
                        "Password tidak memiliki huruf kapital"

                } else {
                    viewBind.sandiBaru.error = ""
                }

                if (passwordVerifikasi == lama) {
                    arrayListUser.users.set(indexSekarang, user)
                    val myIntent = Intent(this, MainActivity::class.java).apply {
                    }
                    Toast.makeText(this, "Password Berhasil DiGanti", Toast.LENGTH_LONG).show()
                    myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(myIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Password Lama Tidak Sama", Toast.LENGTH_LONG).show()

                }
            }


        }
    }


    fun back() {
        viewBind.backDTP.setOnClickListener() {
            finish()
        }
    }

    fun batal() {
        viewBind.batalDTP.setOnClickListener() {
            finish()
        }
    }


}