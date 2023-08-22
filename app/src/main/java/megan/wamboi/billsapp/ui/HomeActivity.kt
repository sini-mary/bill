package megan.wamboi.billsapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import megan.wamboi.billsapp.R
import megan.wamboi.billsapp.databinding.ActivityHomeBinding
import megan.wamboi.billsapp.databinding.ActivityMainBinding
import utils.Constants

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
            val sharedprefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
            val logoutbutton=findViewById<Button>(R.id.btnExample)
            logoutbutton.setOnClickListener{
                val editor = sharedprefs.edit()
                editor.clear()
                editor.apply()
                val loginActivity2= Intent(this@HomeActivity, LoginActivity2::class.java)
                startActivity(loginActivity2)
                finish()
            }



}}