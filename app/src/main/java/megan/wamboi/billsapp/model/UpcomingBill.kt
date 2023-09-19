package megan.wamboi.billsapp.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName= "UpComingBills",
    indices = [Index(value = ["billId","dueDate"], unique = true)])
data class UpcomingBill(
    @PrimaryKey var upcomingBillId:   String,
    var billId:String,
    var name:String,
    var amount:Double,
    var frequency:String,
    var dueDate: String,
    var userId:String,
    var paid:Boolean
)
