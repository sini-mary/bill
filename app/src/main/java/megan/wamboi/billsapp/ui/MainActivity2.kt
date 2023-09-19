package megan.wamboi.billsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import megan.wamboi.billsapp.R
import megan.wamboi.billsapp.databinding.ActivityMain2Binding
import megan.wamboi.billsapp.databinding.FragmentSummaryBinding
import megan.wamboi.billsapp.viewmodel.BillsViewModel

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    val billsViewModel :BillsViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        setupBottomNav()
        billsViewModel.createUpcomingBills()

    }
    fun setupBottomNav(){
        binding.btnvhome.setOnItemSelectedListener {
            menuitem->
            when(menuitem.itemId){
                R.id.summary->{
                    supportFragmentManager.beginTransaction().replace(R.id.fvchome,summaryFragment())
                        .commit()
                    true

                }
                R.id.upcomingbills-> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fvchome, upcomingBills())
                        .commit()
                    true
                }
                R.id.paid-> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fvchome, PaidBillsFragment())
                        .commit()
                    true
                }
                R.id.settings-> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fvchome, SettingsFragment())
                        .commit()
                    true
                }

                    else->{
                    false
                }
            }
        }
    }
}