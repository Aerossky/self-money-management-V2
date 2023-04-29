package com.example.myapplication

import Model.User
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivitySignUpBinding
    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        registerClick()
        goToLogin()
    }

    private fun registerClick() {
        viewBind.registerButton.setOnClickListener {
            var nama=viewBind.namaTextInputLayout.editText?.text!!.toString().trim()
            var email=viewBind.emailTextInputLayout.editText?.text!!.toString().trim()
            var password=viewBind.passwordTextInputLayout.editText?.text!!.toString().trim()

            user=User(nama, email, password)

            checker()
        }
    }

    private fun checker() {
        var isCompleted=true
        //nama
        if (user.nama?.isEmpty() == true) {
            viewBind.namaTextInputLayout.error="Tolong isi kolom nama"
            isCompleted=false
        } else {
            viewBind.namaTextInputLayout.error=""
        }

        //Email
        if (user.email!!.isEmpty()) {
            viewBind.emailTextInputLayout.error="Tolong isi kolom email"
            isCompleted=false
        } else {
            // Berguna untuk cek apakah input merupakan email
            if (!Patterns.EMAIL_ADDRESS.matcher(user.email.toString()).matches()) {
                viewBind.emailTextInputLayout.error="Tolong masukan alamat email yang benar"
                isCompleted=false
            } else {
                viewBind.emailTextInputLayout.error=""
            }
        }
        //Password
        if (user.password?.isEmpty() == true) {
            viewBind.passwordTextInputLayout.error="Tolong isi kolom password"
            isCompleted=false
        } else {
            if (user.password!!.length < 8) {
                viewBind.passwordTextInputLayout.error="Jumlah password min 8 karakter"
                isCompleted=false
            } else if (!user.password!!.matches(".*[a-z].*".toRegex())) {
                viewBind.passwordTextInputLayout.error="Password tidak memiliki huruf kecil"
                isCompleted=false
            } else if (!user.password!!.matches(".*[A-Z].*".toRegex())) {
                viewBind.passwordTextInputLayout.error=
                    "Password tidak memiliki huruf kapital"
                isCompleted=false
            } else {
                viewBind.passwordTextInputLayout.error=""
            }
        }
        if (isCompleted) {
            arrayListUser.users.add(user)
            val myIntent=Intent(this, loginActivity::class.java).apply {
            }
            Toast.makeText(this, "Sign up successful, please login", Toast.LENGTH_LONG).show()
            myIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)
            finish()
        }
    }

    private fun goToLogin() {
        viewBind.masukdariRegisterTextView.setOnClickListener {
            val myIntent=Intent(this, loginActivity::class.java).apply {

            }
            myIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(myIntent)
            finish()
        }
    }
}
