package megan.wamboi.billsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import megan.wamboi.billsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.button.setOnClickListener {
            val intent= Intent(this,LoginActivity2::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            validateLogin()
            clearErrors()


        }

    }

    fun validateLogin(){
        val username = binding.etusername.text.toString()
        val phoneNumber = binding.etphonenumber.text.toString()
        val email = binding.etemail.text.toString()
        val password =  binding.etpassword.text.toString()
        var error = false

        if (username.isBlank()) {
            binding.tilusername.error = "Enter username"
            error = true
        }

        if (phoneNumber.isBlank()) {
            binding.tilphonenumber.error = "Enter phone number"
            error = true
        }

        if (email.isBlank()) {
            binding.tilemailpassword.error = "Enter email"
            error = true
        }

        if (password.isBlank()) {
            binding.tilpassword.error = "Enter your password"
            error = true
        }

        if (!error){
            Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
        }

    }

    fun clearErrors() {
        binding.tilusername.error = null
        binding.tilphonenumber.error = null
        binding.tilemailpassword.error = null
        binding.tilpassword.error = null

    }

}