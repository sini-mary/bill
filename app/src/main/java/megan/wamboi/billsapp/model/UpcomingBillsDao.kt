package megan.wamboi.billsapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UpcomingBillsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUpcomingBill(upcomingBill: UpcomingBill)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUpcomingBills(upcomingBills: UpcomingBill)

    @Query("SELECT * FROM UpcomingBills WHERE billId = :billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExistingBills(billId: String, startDate: String, endDate: String): List<UpcomingBill>

    @Query("SELECT * FROM UpComingBills WHERE frequency = :freq  AND paid = 0 ORDER BY dueDate")
    fun getUpcomingBillsByFrequency(freq:String,paid:Boolean):LiveData<List<UpcomingBill>>
}
