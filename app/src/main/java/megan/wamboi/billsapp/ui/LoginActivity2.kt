package megan.wamboi.billsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import megan.wamboi.billsapp.databinding.ActivityLogin2Binding
import megan.wamboi.billsapp.model.LoginRequest
import megan.wamboi.billsapp.viewmodel.LoginViewModel

class LoginActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityLogin2Binding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogins.setOnClickListener {
            validate()
            clearErrors()
        }
        loginViewModel.errorLiveData.observe(this, Observer { err->
            Toast.makeText(this,err, Toast.LENGTH_LONG).show()
            binding.pbLoad.visibility = View.GONE
        })

        loginViewModel.logLiveData.observe(this, Observer { logResponse->
            binding.pbLoad.visibility = View.GONE
            Toast.makeText(this,logResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))
        })
    }

    private fun validate(){
        val email = binding.etEmails.text.toString()
        val password = binding.etPasswords.text.toString()
        var error = false

        if(email.isBlank()){
            binding.tilEmails.error = "Enter your username"
            error = true
        }

        if(password.isBlank()){
            binding.tilPasswords.error = "Enter your password"
            error = true
        }

        if (!error) {
            if (!error) {
                val loginRequest = LoginRequest(
                    email = email,
                    password = password
                )
                binding.pbLoad.visibility = View.VISIBLE
                loginViewModel.loginUser(loginRequest)
            }
        }
    }

    fun clearErrors(){
        binding.tilEmails.error = null
        binding.tilPasswords.error = null
    }


}