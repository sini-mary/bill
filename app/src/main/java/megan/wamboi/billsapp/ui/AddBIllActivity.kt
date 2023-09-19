import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import megan.wamboi.billsapp.databinding.ActivityAddBillBinding
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.viewmodel.BillsViewModel
import utils.Constants
import java.util.UUID



class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    var selectedMonth = 0
    var selectedDate = 0
    val billsViewModel:BillsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupnav()
    }
    fun setupnav(){
        setSupportActionBar(binding.toolbaraddBIll)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        setUpFreqSpinner()
        binding.btnAddBill.setOnClickListener{
          validateBill()

        }
    }

    fun setUpFreqSpinner(){
        val freq= arrayOf(Constants.WEEKLY,Constants.MONTHLY,Constants.YEARLY)
        val freqAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,freq)
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter=freqAdapter
        binding.spinner.onItemSelectedListener =object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(binding.spinner.selectedItem.toString()){
                    Constants.WEEKLY->{
                        showSpinner()
                        setUpDueDateSpinner(Array(7){it +1})
                    }
                    Constants.MONTHLY->{
                        showSpinner()
                        setUpDueDateSpinner(Array(31){it+1})

                    }
                    Constants.YEARLY->{
                        showDatePicker()
                        setupDpDueDate()

                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
     fun showSpinner(){
         binding.dpduedate.hide()
         binding.spDueDate.show()



     }
    fun showDatePicker(){
        binding.dpduedate.show()
        binding.spDueDate.hide()
    }
    fun View.show(){
        this.visibility=View.VISIBLE
    }
    fun View.hide(){
        this.visibility=View.GONE
    }
    fun setUpDueDateSpinner(dates:Array<Int>){
        val dueDateAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDueDate.adapter =dueDateAdapter
    }
    fun setupDpDueDate(){
        val cal =Calendar.getInstance()
        binding.dpduedate.init(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ) { _, _, month, date ->
            selectedDate = date
            selectedMonth = month+1
        }


    }
    fun validateBill(){
        val name = binding.tilName.text.toString()
        val amount = binding.tilAmount.text.toString()
        val frequency = binding.spinner.selectedItem.toString()
        val dueDate = if(frequency==Constants.YEARLY){
            var finalDate= selectedDate.toString()
            var finalmonth =selectedDate.toString()
            if (selectedDate<10){
                finalDate ="0$selectedDate"
            }
            if (selectedMonth<10){
                finalmonth ="0$selectedDate"
            }
             "$selectedDate/$selectedMonth"
        } else{
            binding.spDueDate.selectedItem.toString()
        }

        var error = false
        if(name.isBlank()){
            error = true
            binding.tilName.error = "Name is required"
        }
        if(amount.isBlank()){
            error = true
            binding.tilName.error = "Amount is required"
        }
        if(!error){
            val prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
            val userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)
            val newBill = Bill(
                name = name,
                amount = amount.toDouble(),
                frequency = frequency,
                dueDate = dueDate,
                billid = UUID.randomUUID().toString(),

            )
            billsViewModel.saveBill(newBill)
            clearForm()
        }
    }

    fun clearForm(){
        binding.tilName.setText(Constants.EMPTY_STRING)
        binding.tilAmount.setText(Constants.EMPTY_STRING)
        binding.spinner.setSelection(0)
        showSpinner()
        binding.spDueDate.setSelection(0)
    }
}
