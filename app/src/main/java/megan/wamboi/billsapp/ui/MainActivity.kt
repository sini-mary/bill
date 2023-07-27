package megan.wamboi.billsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import megan.wamboi.billsapp.databinding.ActivityMainBinding
import megan.wamboi.billsapp.model.RegisterRequest
import megan.wamboi.billsapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity2::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            validateSignup()
            clearErrors()
        }
        userViewModel.errLiveData.observe(this, Observer { err->
            Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })

        userViewModel.regLiveData.observe(this, Observer { regResponse->
            binding.pbRegister.visibility = View.GONE
            Toast.makeText(this,regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,LoginActivity2::class.java))
        })
    }

    private fun validateSignup() {
        val firstName = binding.etFirstName.text.toString()
        val name = binding.etName.text.toString()
        val phone = binding.etPhone.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        var error = false

        if (name.isBlank()) {
            binding.tilName.error = "Enter name"
            error = true
        }
        if (phone.isBlank()) {
            binding.tilPhone.error = "Enter Phone number"
            error = true
        }

        if (email.isBlank()) {
            binding.tilEmail.error = "Enter email adress"
            error = true
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Enter a password"
            error = true
        }

        if(firstName.isBlank()){
            binding.tilFirstName.error = "Enter your first Name"
            error = true
        }

        if(confirmPassword != password){
            binding.tilConfirmPassword.error = "Password do not match"
            error = true
        }

        if (!error) {
            val registerRequest = RegisterRequest(
                firstName = firstName,
                email = email,
                password = password,
                phoneNumber = phone,
                lastName = name
            )
            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }

    }

    private fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilName.error = null
        binding.tilPhone.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
    }
}
