package com.example.myapplication

import ModelUang.Pemasukan
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityTambahPemasukanBinding
import kotlin.properties.Delegates

class TambahPemasukanActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityTambahPemasukanBinding
    private lateinit var pemasukan: Pemasukan
    private var kolomUang=true
    private var kolomTanggal=true
    var indexUser=arrayListUser.indexUser
    private var indexUangList by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPemasukanBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        viewBind.simpanPemasukanButton.isEnabled=false
        viewBind.simpanPemasukanButton.setBackgroundColor(Color.GRAY)

        //fuction
        listenerAddPemasukan()
        terimaData()
        checkInput()
        back()
    }

    private fun terimaData() {
        //dikasih -1 supaya jika dapat index 0 masih kebaca dan membedakan index 0 dengan default value
        indexUangList=intent.getIntExtra("position", -1)
        if (indexUangList > -1) {
            var data=arrayListUser.users.get(indexUser).uanglist.get(indexUangList)
            if (data is Pemasukan) {
                viewBind.nominalPemasukanTextInputLayout.editText?.setText(data.jumlahUang.toString())
                viewBind.tanggalPemasukanTextInputLayout.editText?.setText(data.tanggal)
                viewBind.catatanPemasukanTextInputLayout.editText?.setText(data.catatan)
            }
        }
    }

    private fun listenerAddPemasukan() {
        viewBind.simpanPemasukanButton.setOnClickListener {
            var pengenal="pemasukan"
            var nominalPemasukan=
                viewBind.nominalPemasukanTextInputLayout.editText?.text!!.toString().toInt()
            var tanggalPemasukan=
                viewBind.tanggalPemasukanTextInputLayout.editText?.text!!.toString()
            var catatanPemasukan=
                viewBind.catatanPemasukanTextInputLayout.editText?.text!!.toString()
            if (catatanPemasukan.isEmpty() == true) {
                catatanPemasukan=""
            }
            if (indexUangList > -1) {
                pemasukan=Pemasukan(pengenal, nominalPemasukan, tanggalPemasukan, catatanPemasukan)
                arrayListUser.users.get(indexUser).uanglist.set(indexUangList, pemasukan)
            } else {
                pemasukan=Pemasukan(pengenal, nominalPemasukan, tanggalPemasukan, catatanPemasukan)
                arrayListUser.users.get(indexUser).uanglist.add(pemasukan)
            }
            Toast.makeText(this, "Berhasil Di Simpan", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun checkInput() {
        viewBind.nominalPemasukanTextInputLayout.editText?.addTextChangedListener {
            kolomUang=
                viewBind.nominalPemasukanTextInputLayout.editText?.text.toString().isEmpty()

            if (kolomUang || kolomTanggal) {
                viewBind.simpanPemasukanButton.isEnabled=false
                viewBind.simpanPemasukanButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.simpanPemasukanButton.isEnabled=true
                viewBind.simpanPemasukanButton.setBackgroundColor(Color.GREEN)
            }
        }

        viewBind.tanggalPemasukanTextInputLayout.editText?.addTextChangedListener {
            kolomTanggal=
                viewBind.tanggalPemasukanTextInputLayout.editText?.text.toString().isEmpty()

            if (kolomUang || kolomTanggal) {
                viewBind.simpanPemasukanButton.isEnabled=false
                viewBind.simpanPemasukanButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.simpanPemasukanButton.isEnabled=true
                viewBind.simpanPemasukanButton.setBackgroundColor(Color.rgb(46, 143, 0))
            }
        }
    }

    private fun back() {
        viewBind.backPemasukanButton.setOnClickListener {
            finish()
        }
    }
}