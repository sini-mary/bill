package megan.wamboi.billsapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.model.UpcomingBill
import megan.wamboi.billsapp.model.UpcomingBillsDao
import megan.wamboi.billsapp.repository.BillzRepository

class BillsViewModel:ViewModel (){
    val billsrepo= BillzRepository()
    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsrepo.saveBill(bill)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createUpcomingBills(){
        viewModelScope.launch {
            billsrepo.createRecurringMonthlyBills()
            billsrepo.createReccurringWeeklyBills()
            billsrepo.createRecurringAnnualBills()
        }
    }

    fun getWeeklyUpcoming(): UpcomingBillsDao {
        return  billsrepo.upcomingBillsDao

    }

}