package megan.wamboi.billsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import megan.wamboi.billsapp.R
import megan.wamboi.billsapp.databinding.ActivityAddBillBinding
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.viewmodel.BillsViewModel

class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billsViewModel:BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddBill.setOnClickListener {
            val intent =Intent(this@AddBillActivity,summaryFragment::class.java)
            validateDetails()
            startActivity(intent)

        }
        // Move the following lines inside the onCreate method
        val freq = resources.getStringArray(R.array.Frequency)
        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, freq
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.selected_item) + " " +
                                freq[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // You can add code here if needed
                }
            }
        }
    }
    fun validateDetails() {
        val billname = binding.tilName.text.toString()
        val billamount = binding.tilAmount.text.toString()
        val frequency = binding.spinner.adapter
        val duedate = binding.etDueDate
        var error = false
        if (billname.isBlank()) {
            binding.tilName.error = "Bill name is required"
            error = true

        } else {
            binding.tilName.error = null
        }
        if (billamount.isBlank()) {
            binding.tilAmount.error = "Bill Amount is required"
            error = true

        } else {
            binding.tilAmount.error = null
        }

        if (!error) {
    val newbill= Bill(name =billname, amount = billamount, dueDate =duedate, frequency = frequency, billid = "")
            billsViewModel.saveBill(newbill)
            Toast.makeText(this,"Bill saved",Toast.LENGTH_LONG).show()
            finish()
        }

    }

}
