package com.example.myapplication

import Adapter.ListDataRVAdapter
import Interface.CardListener
import Model.User
import ModelUang.Pengeluaran
import ModelUang.Uang
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityRecyclerViewBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityRecyclerViewBinding
    private lateinit var user: User
    private lateinit var adapter: ListDataRVAdapter
    private lateinit var fromActivity: String
    private var dataPemasukan=ArrayList<Uang>()
    private var dataPengeluaran=ArrayList<Uang>()

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        var indexUserHome=arrayListUser.indexUser
        user=arrayListUser.users.get(indexUserHome)
        fromActivity=intent.getStringExtra("from") ?: ""

        manageData()
        setupRecyclerView()
        setPP()
    }

    private fun manageData() {
        dataPemasukan=ArrayList()
        dataPengeluaran=ArrayList()
        for (data in user.uanglist) {
            if (data is Pengeluaran) {
                dataPengeluaran.add(data)
            } else {
                dataPemasukan.add(data)
            }
        }
        if (fromActivity.equals("Pengeluaran")) {
            adapter=ListDataRVAdapter(dataPengeluaran, this, fromActivity)
        } else {
            adapter=ListDataRVAdapter(dataPemasukan, this, fromActivity)
        }
        viewBind.ListDataRV.adapter=adapter   // Set adapter
    }

    private fun setupRecyclerView() {
        val layoutManager=LinearLayoutManager(baseContext)
        viewBind.ListDataRV.layoutManager=layoutManager   // Set layout
    }

    private fun setPP() {
        var indexUserHome=arrayListUser.indexUser
        var pemtot=0
        var pengtot=0
        for (i in arrayListUser.users.get(indexUserHome).uanglist) {
            if (i.pengenal.equals("pengeluaran")) {
                pengtot+=i.jumlahUang
            } else if (i.pengenal.equals("pemasukan")) {
                pemtot+=i.jumlahUang
            }
        }
        if (fromActivity.equals("Pengeluaran")) {
            viewBind.titleRVTextView.text="Pengeluaran Kamu"
            viewBind.kategoriRVTextView.text="Kategori"
            viewBind.totalRVTextView.text=pengtot.convertRupiah()
            viewBind.addRVButton.setOnClickListener {
                val myIntent=Intent(this, TambahPengeluaranActivity::class.java)
                startActivity(myIntent)
            }
        } else {
            viewBind.titleRVTextView.text="Pemasukan Kamu"
            viewBind.kategoriRVTextView.text="Catatan"
            viewBind.totalRVTextView.text=pemtot.convertRupiah()
            viewBind.addRVButton.setOnClickListener {
                val myIntent=Intent(this, TambahPemasukanActivity::class.java)
                startActivity(myIntent)
            }
        }
    }

    override fun onCardClick(position: Int) {
        val myIntent=Intent(this, ViewActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }

    override fun onResume() {
        super.onResume()
        manageData()
        setPP()
        adapter.notifyDataSetChanged()
    }
}