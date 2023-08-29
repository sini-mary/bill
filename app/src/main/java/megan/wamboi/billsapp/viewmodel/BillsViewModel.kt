package megan.wamboi.billsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.repository.BillzRepository

class BillsViewModel:ViewModel (){
    val billsrepo= BillzRepository()
    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsrepo.saveBill(bill)
        }
    }

}