package megan.wamboi.billsapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import megan.wamboi.billsapp.BillsApp
import megan.wamboi.billsapp.database.BillsDB
import megan.wamboi.billsapp.model.Bill

class BillzRepository {
    val db = BillsDB.getDatabase(BillsApp.appContext)
    val billsDao =db.billsdao()

    suspend fun saveBill(bill:Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }
}