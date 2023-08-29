package megan.wamboi.billsapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import megan.wamboi.billsapp.databinding.ActivityLogin2Binding
import megan.wamboi.billsapp.model.LoginRequest
import megan.wamboi.billsapp.model.LoginResponse
import megan.wamboi.billsapp.viewmodel.LoginViewModel
import megan.wamboi.billsapp.viewmodel.UserViewModel
import retrofit2.Response
import utils.Constants

class LoginActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityLogin2Binding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        onResume()
    }

    override fun onResume() {
        super.onResume()
        clearErrors()

        binding.btnLogins.setOnClickListener {
            val intent= Intent(this@LoginActivity2 ,MainActivity2::class.java)
            startActivity(intent)
            validate()

        }
        initobservers()
        loginViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.btnLogins.visibility = View.GONE
        })

        loginViewModel.logLiveData.observe(this, Observer { logResponse ->
            persistlogin(logResponse)
            binding.btnLogins.visibility = View.GONE
            Toast.makeText(this, logResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity2::class.java))
        })
    }

    private fun validate() {
        val email = binding.etEmails.text.toString()
        val password = binding.etPasswords.text.toString()
        var error = false

        if (email.isBlank()) {
            binding.tilEmails.error = "Enter your username"
            error = true
        } else {
            binding.tilEmails.error = null
        }

        if (password.isBlank()) {
            binding.tilPasswords.error = "Enter your password"
            error = true
        } else {
            binding.tilPasswords.error = null
        }

        if (!error) {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
          loginViewModel.loginUser(loginRequest)
        }
    }



//when loggin out override the existing values with blanks
    fun persistlogin(loginResponse: LoginResponse){
        val sharedPrefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
        editor.putString(Constants.USER_ID,loginResponse.user_id)
        editor.putString(Constants.ACCESS_TOKEN,loginResponse.access_token)
        //writes the values to the file
        editor.apply()
    }
    fun clearErrors() {
      binding.etEmails.addTextChangedListener(object :TextWatcher{
          override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              binding.etEmails.error=null
          }

          override fun afterTextChanged(p0: Editable?) {
          }

          override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              binding.etEmails.error= null
          }
      })
        binding.etPasswords.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilPasswords.error=null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilPasswords.error=null
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    fun initobservers(){
        loginViewModel.logLiveData.observe(this){loginresponse->
            persistlogin(loginresponse)
//            Toast.makeText( this, Toast.LENGTH_LONG).show()
        }
        loginViewModel.errorLiveData.observe(this){
            error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }

}