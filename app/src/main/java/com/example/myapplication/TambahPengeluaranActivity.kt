package com.example.myapplication

import ModelUang.Pemasukan
import ModelUang.Pengeluaran
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityTambahPengeluaranBinding
import kotlin.properties.Delegates

class TambahPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityTambahPengeluaranBinding
    lateinit var option: Spinner
    lateinit var result: TextView
    private lateinit var pengeluaran: Pengeluaran
    private var kolomUang = true
    private var kolomTanggal = true
    var indexUser=arrayListUser.indexUser
    private var indexUangList by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        //button
        viewBind.simpanPengeluaranButton.isEnabled = false
        viewBind.simpanPengeluaranButton.setBackgroundColor(Color.GRAY)
        //dropdown
        option=findViewById(R.id.dropdown_pengeluaran) as Spinner
        result=findViewById(R.id.showPengeluaran) as TextView

        val kategoriPengeluaran=arrayOf("Makan", "Kuliah", "Hiburan", "Perlengkapan", "Lain-lain")

        option.adapter=
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kategoriPengeluaran)

        option.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text=kategoriPengeluaran.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text=""
            }
        }
        //function
        listenerAddPengeluaran()
        terimaData()
        checkInput()
        backActivity()
    }

    private fun terimaData() {
        //dikasih -1 supaya jika dapat index 0 masih kebaca dan membedakan index 0 dengan default value
        indexUangList=intent.getIntExtra("position", -1)
        if (indexUangList > -1) {
            var data=arrayListUser.users.get(indexUser).uanglist.get(indexUangList)
            if (data is Pemasukan) {
                viewBind.nominalPengeluaranTextInputLayout.editText?.setText(data.jumlahUang.toString())
                viewBind.tanggalPengeluaranTextInputLayout.editText?.setText(data.tanggal)
                viewBind.catatanPengeluaranTextInputLayout.editText?.setText(data.catatan)
            }
        }
    }

    private fun listenerAddPengeluaran() {
        viewBind.simpanPengeluaranButton.setOnClickListener {
            var pengenal="pengeluaran"
            var nominalPengeluaran=
                viewBind.nominalPengeluaranTextInputLayout.editText?.text!!.toString().toInt()
            var kategori=viewBind.showPengeluaran.text.toString()
            var tanggalPengeluaran=
                viewBind.tanggalPengeluaranTextInputLayout.editText?.text!!.toString()
            var catatanPengeluaran=
                viewBind.catatanPengeluaranTextInputLayout.editText?.text!!.toString()

            if (catatanPengeluaran.isEmpty() == true) {
                catatanPengeluaran = ""
            }

            if (indexUangList > -1) {
                pengeluaran=Pengeluaran(pengenal, nominalPengeluaran, kategori, tanggalPengeluaran, catatanPengeluaran)
                arrayListUser.users.get(indexUser).uanglist.set(indexUangList, pengeluaran)
            }else{
                pengeluaran=Pengeluaran(pengenal, nominalPengeluaran, kategori, tanggalPengeluaran, catatanPengeluaran)
                arrayListUser.users.get(indexUser).uanglist.add(pengeluaran)
            }
            Toast.makeText(this, "Berhasil Di Simpan", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun checkInput() {
        viewBind.nominalPengeluaranTextInputLayout.editText?.addTextChangedListener {
            kolomUang=
                viewBind.nominalPengeluaranTextInputLayout.editText?.text.toString().isEmpty()

            if (kolomUang || kolomTanggal) {
                viewBind.simpanPengeluaranButton.isEnabled=false
                viewBind.simpanPengeluaranButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.simpanPengeluaranButton.isEnabled=true
                viewBind.simpanPengeluaranButton.setBackgroundColor(Color.GREEN)
            }
        }

        viewBind.tanggalPengeluaranTextInputLayout.editText?.addTextChangedListener {
            kolomTanggal=
                viewBind.tanggalPengeluaranTextInputLayout.editText?.text.toString().isEmpty()

            if (kolomUang || kolomTanggal) {
                viewBind.simpanPengeluaranButton.isEnabled=false
                viewBind.simpanPengeluaranButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.simpanPengeluaranButton.isEnabled=true
                viewBind.simpanPengeluaranButton.setBackgroundColor(Color.rgb(46, 143, 0))
            }
        }
    }

    private fun backActivity() {
        viewBind.backButton2.setOnClickListener {
            finish()
        }
    }
}