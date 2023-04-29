package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var viewBind: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        //function
        showUser()
        backSetting()
        ubahPWD()
        hapusAkun()
        logOut()
        return viewBind.root
    }

    private fun backSetting() {
        viewBind.backSetting.setOnClickListener {
            val myIntent =
                Intent(this@SettingsFragment.requireContext(), MainActivity::class.java)
            startActivity(myIntent)
            activity?.finish()
        }
    }

    private fun ubahPWD() {
        viewBind.ubahPasswordCardView.setOnClickListener {
            val myIntent =
                Intent(this@SettingsFragment.requireContext(), DetailProfil::class.java)

            startActivity(myIntent)
        }
    }

    private fun hapusAkun() {
        viewBind.hapusAkunCardView.setOnClickListener {
            val myIntent =
                Intent(this@SettingsFragment.requireContext(), DeleteUserActivity::class.java)

            startActivity(myIntent)
        }

    }

    private fun showUser() {
        var indexUserSetting = arrayListUser.indexUser
        viewBind.namaTextView.text = arrayListUser.users.get(indexUserSetting).nama
        viewBind.emailTextView.text = arrayListUser.users.get(indexUserSetting).email
    }

    private fun logOut() {
        viewBind.logoutButton.setOnClickListener {
            val myIntent =
                Intent(this@SettingsFragment.requireContext(), homeActivity::class.java)
            startActivity(myIntent)
            activity?.finish()

        }
    }
}