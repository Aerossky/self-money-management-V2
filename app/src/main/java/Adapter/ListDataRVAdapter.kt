package Adapter

import Interface.CardListener
import ModelUang.Pemasukan
import ModelUang.Pengeluaran
import ModelUang.Uang
import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CardUangBinding
import java.util.*
import kotlin.collections.ArrayList

class ListDataRVAdapter(val listUang: ArrayList<Uang>, val cardListener: CardListener, val myIntent: String) : RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {
    class viewHolder(val itemView: View, val cardListener1: CardListener) :
        RecyclerView.ViewHolder(itemView) {

        val binding=CardUangBinding.bind(itemView)

        fun Any.convertRupiah(): String {
            val localId = Locale("in", "ID")
            val formatter = NumberFormat.getCurrencyInstance(localId)
            val strFormat = formatter.format(this)
            return strFormat
        }

        fun setData(data: Uang, myIntent: String) {
            if (myIntent.equals("Pengeluaran")) {
                if (data is Pengeluaran) {
                    binding.kategoriCard.text=data.kategori
                    binding.nominalUangCard.text=data.jumlahUang.convertRupiah()
                }
            } else {
                if (data is Pemasukan) {
                    binding.kategoriCard.text=data.catatan
                    binding.nominalUangCard.text=data.jumlahUang.convertRupiah()
                }
            }

            itemView.setOnClickListener {
                cardListener1.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.card_uang, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listUang[position], myIntent)
    }

    override fun getItemCount(): Int {
        return listUang.size
    }
}