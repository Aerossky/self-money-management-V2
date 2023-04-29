package com.example.myapplication

import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityTambahAutoMaticManageBinding
import java.util.*


class TambahAutoMaticManageActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityTambahAutoMaticManageBinding
    lateinit var option: Spinner
    lateinit var result: TextView
    private var isUangBulananIsEmpty = true
    private var isPersenIsEmpty = true

    @RequiresApi(Build.VERSION_CODES.N)
    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityTambahAutoMaticManageBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        viewBind.hitungButton.isEnabled = false
        viewBind.hitungButton.setBackgroundColor(Color.GRAY)


        option = findViewById(R.id.dropdown) as Spinner
        result = findViewById(R.id.show) as TextView

        val rangeWaktu = arrayOf("1 Minggu", "2 Minggu", "3 Minggu", "4 Minggu")

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rangeWaktu)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text = rangeWaktu.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = ""
            }
        }
        //function
        listener()
        checkInput()
        back()

    }

    fun back() {
        viewBind.backMM.setOnClickListener {

            finish()

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun listener() {
        viewBind.hitungButton.setOnClickListener {
            var uangBulanan =
                viewBind.uangBulananTextInputLayout.editText?.text!!.toString().toInt()
            var persen =
                viewBind.presentaseYGdiTabungTextInputLayout.editText?.text!!.toString().toInt()
            var isCompleted: Boolean = true

            //check persen
            if (persen >= 100 || persen <= 0) {
                viewBind.presentaseYGdiTabungTextInputLayout.error = "persentase melebihi dari 100%"
                Toast.makeText(
                    this,
                    "kurang atau lebih dari batas persentase",
                    Toast.LENGTH_SHORT
                ).show()
                isCompleted = false
            } else {
                viewBind.presentaseYGdiTabungTextInputLayout.error = ""
            }

            // check
            if (isCompleted) {
//                var uangsimpan = uangBulanan * (persen / 100)
                var uangsimpan = uangBulanan * (persen.toDouble() / 100)
                var uangpakai = uangBulanan - uangsimpan
                var minggu = viewBind.show.text
                var waktu = 0
                var hasil: Double?

                if (minggu.equals("1 Minggu")) {
                    viewBind.texthasil.text = "Hasil Hitungan 1 Minggu"
                    waktu = 7
                    hasil = uangpakai / waktu
//                    hasil = uangsimpan.toDouble()
                    viewBind.hasilTextView.text = hasil.convertRupiah() + " /hari"
                } else if (minggu.equals("2 Minggu")) {
                    viewBind.texthasil.text = "Hasil Hitungan 2 Minggu"
                    waktu = 14
                    hasil = uangpakai / waktu
                    viewBind.hasilTextView.text = hasil.convertRupiah() + " /hari"
                } else if (minggu.equals("3 Minggu")) {
                    viewBind.texthasil.text = "Hasil Hitungan 3 Minggu"
                    waktu = 21
                    hasil = uangpakai / waktu
                    viewBind.hasilTextView.text = hasil.convertRupiah() + " /hari"
                } else if (minggu.equals("4 Minggu")) {
                    viewBind.texthasil.text = "Hasil Hitungan 4 Minggu"
                    waktu = 28
                    hasil = uangpakai / waktu
                    viewBind.hasilTextView.text = hasil.convertRupiah() + " /hari"
                }
            }
        }

    }

    private fun checkInput() {
        viewBind.uangBulananTextInputLayout.editText?.addTextChangedListener {
            isUangBulananIsEmpty =
                viewBind.uangBulananTextInputLayout.editText?.text.toString().isEmpty()

            if (isUangBulananIsEmpty || isPersenIsEmpty) {
                viewBind.hitungButton.isEnabled = false
                viewBind.hitungButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.hitungButton.isEnabled = true
                viewBind.hitungButton.setBackgroundColor(Color.GREEN)
            }
        }

        viewBind.presentaseYGdiTabungTextInputLayout.editText?.addTextChangedListener {
            isPersenIsEmpty =
                viewBind.presentaseYGdiTabungTextInputLayout.editText?.text.toString().isEmpty()

            if (isUangBulananIsEmpty || isPersenIsEmpty) {
                viewBind.hitungButton.isEnabled = false
                viewBind.hitungButton.setBackgroundColor(Color.GRAY)
            } else {
                viewBind.hitungButton.isEnabled = true
                viewBind.hitungButton.setBackgroundColor(Color.rgb(46, 143, 0))
            }
        }
    }
}