package com.example.myapplication

import android.content.Intent
import android.icu.text.NumberFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

        private lateinit var viewBind: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.N)

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind=FragmentHomeBinding.inflate(layoutInflater, container, false)

        welcome()
        showData()
        tryMove()

        return viewBind.root
    }

    //    kondisi pagi siang malam
    @RequiresApi(Build.VERSION_CODES.N)
    private fun welcome() {
        var indexUserHome=arrayListUser.indexUser
        var user=arrayListUser.users.get(indexUserHome).nama
        val currentHour=Calendar.getInstance().get(Calendar.HOUR)
        viewBind.homeWelcomeUserTextView.text=if (currentHour > 18 && currentHour < 4) {
            ("Malam " + user)
        } else if (currentHour > 12 && currentHour < 15) {
            ("Siang " + user)
        } else {
            ("Pagi " + user)
        }
    }

    private fun tryMove() {
        viewBind.pemasukanHomeCardView.setOnClickListener {
            val myIntent=
                Intent(this@HomeFragment.requireContext(), RecyclerViewActivity::class.java).apply {
                    putExtra("from", "Pemasukan")
                }
            startActivity(myIntent)
        }
        viewBind.pengeluaranHomeCardView.setOnClickListener {
            val myIntent=
                Intent(this@HomeFragment.requireContext(), RecyclerViewActivity::class.java).apply {
                    putExtra("from", "Pengeluaran")
                }

            startActivity(myIntent)
        }
    }

    private fun showData() {
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
        viewBind.pemtotHomeTextView.text=pemtot.convertRupiah()
        viewBind.pengtotHomeTextView.text=pengtot.convertRupiah()
        var totalSaldo=pemtot - pengtot
        viewBind.totalBalanceHomeTextView.text=totalSaldo.convertRupiah()
    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}