package megan.wamboi.billsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import megan.wamboi.billsapp.model.Bill


@Dao
interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill)
    @Query ("SELECT * FROM Bills WHERE frequency= :freq")
    fun getFrequencyBills(freq:String):List<Bill>
}