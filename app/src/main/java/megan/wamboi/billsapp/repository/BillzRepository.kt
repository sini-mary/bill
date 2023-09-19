package megan.wamboi.billsapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import megan.wamboi.billsapp.BillsApp
import megan.wamboi.billsapp.database.BillsDB
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.model.DateTimeUtils
import megan.wamboi.billsapp.model.UpcomingBill
import utils.Constants
import java.util.Calendar
import java.util.UUID

class BillzRepository {
    val db = BillsDB.getDatabase(BillsApp.appContext)
    val billsDao =db.billsdao()
    val upcomingBillsDao = db.upcomingBillsDao()

    suspend fun saveBill(bill:Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }
    suspend fun insertUpcomingBills(upcomingBills: UpcomingBill) {
        withContext(Dispatchers.IO) {
            upcomingBillsDao.insertUpcomingBill(upcomingBills)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createRecurringMonthlyBills() {
        withContext(Dispatchers.IO) {
            val monthlybills = billsDao.getFrequencyBills(Constants.MONTHLY)
            monthlybills.forEach { bill ->
                val cal = Calendar.getInstance()
                val month = cal.get(Calendar.MONTH) + 1
                val year = cal.get(Calendar.YEAR)
                val startDate = "1/$month/$year"
                val endDate = "31/$month/$year"

                var monthStr= month.toString()
                if (month<10){
                    monthStr =  "0$month"
                }
                if (bill.dueDate.length<2){
                    bill.dueDate = "0${bill.dueDate}"
                }

                val existing = upcomingBillsDao.queryExistingBills(bill.billid, startDate, endDate)
                if (existing.isEmpty()) {
                    val newUpcomingBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId = bill.billid,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDayOfWeek(bill.dueDate),
                        paid = false,
                        userId = ""
                    )
                    insertUpcomingBills(newUpcomingBill)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createReccurringWeeklyBills() {
        withContext(Dispatchers.IO) {
            val weeklyBills = billsDao.getFrequencyBills(Constants.WEEKLY)
            val startDate = DateTimeUtils.getFirstDayOfWeek()
            val endDate = DateTimeUtils.getLastDayOfWeek()
            weeklyBills.forEach { bill ->
                val existing = upcomingBillsDao.queryExistingBills(bill.billid, startDate, endDate)
                if (existing.isEmpty()) {
                    val newUpcomingBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId = bill.billid,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDayOfWeek(bill.dueDate),
                        paid = false,
                        userId = bill.billid
                    )
                    insertUpcomingBills(newUpcomingBill)
                }
            }
        }
    }
@RequiresApi(Build.VERSION_CODES.O)
suspend fun  createRecurringAnnualBills(){
    withContext(Dispatchers.IO){
        val annualBills= billsDao.getFrequencyBills(Constants.YEARLY)
        val year = DateTimeUtils.getCurrentYear()
        val startDate ="01/01/$year"
        val endDate ="31/12/$year"
        annualBills.forEach{bill ->
            val existing =upcomingBillsDao.queryExistingBills(bill.billid,startDate,endDate)
            if(existing.isEmpty()){
                val newannualBill =UpcomingBill(
                    upcomingBillId = UUID.randomUUID().toString(),
                    billId = bill.billid,
                    name = bill.name,
                    amount = bill.amount,
                    frequency = bill.frequency,
                    dueDate = "${bill.dueDate}/$year",
                    paid = false,
                    userId = bill.billid
                )
                upcomingBillsDao.insertUpcomingBills(newannualBill)
            }
        }

    }

    fun getUpcomingBillsbyFrequency():LiveData<List<UpcomingBill>>{
        return upcomingBillsDao.getUpcomingBillsByFrequency(Constants.YEARLY,false)
    }
}
}


