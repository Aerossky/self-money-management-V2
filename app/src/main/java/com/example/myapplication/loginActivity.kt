package com.example.myapplication

import Model.User
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityLoginBinding


class loginActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityLoginBinding
    private lateinit var user: User
    private var isLoginEmailTextInputLayoutEmpty=true
    private var isLoginPasswordTextInputLayoutEmpty=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        viewBind=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        viewBind.loginPageButton.isEnabled=false
        viewBind.loginPageButton.setBackgroundColor(Color.GRAY)

        listener()
        checkInput()
        goToRegister()
    }

    private fun listener() {
        viewBind.loginPageButton.setOnClickListener {
            val email=viewBind.namaLoginTextInputLayout.editText?.text!!.toString().trim()
            val password=viewBind.passwordLoginTextInputLayout.editText?.text!!.toString().trim()
            var check=false
            var ind = 0

            for (item in arrayListUser.users) {
                if (item.email == email && item.password == password) {
                    user=item
                    check=true
                    break
                }
                ind++
            }
            if (check == true) {
                finish()
                arrayListUser.indexUser = ind
                val intent=Intent(this, MainActivity::class.java)
                intent.putExtra("user", user)
                Toast.makeText(this, "Welcome, " + user.nama, Toast.LENGTH_SHORT).show()
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Unable to login, please recheck your email and password",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun checkInput() {
        viewBind.namaLoginTextInputLayout.editText?.addTextChangedListener {
            isLoginEmailTextInputLayoutEmpty=
                viewBind.namaLoginTextInputLayout.editText?.text.toString().isEmpty()

            if (isLoginEmailTextInputLayoutEmpty || isLoginPasswordTextInputLayoutEmpty) {
                viewBind.loginPageButton.isEnabled=false
                viewBind.loginPageButton.setBackgroundColor(Color.BLACK)
            } else {
                viewBind.loginPageButton.isEnabled=true
                viewBind.loginPageButton.setBackgroundColor(Color.WHITE)
            }
        }

        viewBind.passwordLoginTextInputLayout.editText?.addTextChangedListener {
            isLoginPasswordTextInputLayoutEmpty=
                viewBind.passwordLoginTextInputLayout.editText?.text.toString().isEmpty()

            if (isLoginEmailTextInputLayoutEmpty || isLoginPasswordTextInputLayoutEmpty) {
                viewBind.loginPageButton.isEnabled=false
                viewBind.loginPageButton.setBackgroundColor(Color.BLACK)
            } else {
                viewBind.loginPageButton.isEnabled=true
                viewBind.loginPageButton.setBackgroundColor(Color.WHITE)
            }
        }
    }

    private fun goToRegister(){
        viewBind.gotoRegisterTextView.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}