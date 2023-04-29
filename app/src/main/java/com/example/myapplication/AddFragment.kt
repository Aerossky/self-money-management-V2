package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.ActivityDetailProfilBinding
import com.example.myapplication.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var viewBind: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBind=FragmentAddBinding.inflate(layoutInflater, container, false)

        move()

        return viewBind.root
    }

    private fun move() {
        viewBind.automaticAddCardView.setOnClickListener {
            val myIntent= Intent(this@AddFragment.requireContext(), TambahAutoMaticManageActivity::class.java)

            startActivity(myIntent)
        }
        viewBind.pengeluaranAddCardView.setOnClickListener{
            val myIntent= Intent(this@AddFragment.requireContext(), TambahPengeluaranActivity::class.java)

            startActivity(myIntent)
        }
        viewBind.pemasukanAddCardView.setOnClickListener{
            val myIntent= Intent(this@AddFragment.requireContext(), TambahPemasukanActivity::class.java)

            startActivity(myIntent)
        }
//        viewBind.aPlanMakerAddCardView.setOnClickListener{
//            val myIntent= Intent(this@AddFragment.requireContext(), TambahPemasukanActivity::class.java)
//
//            startActivity(myIntent)
//        }
    }
}