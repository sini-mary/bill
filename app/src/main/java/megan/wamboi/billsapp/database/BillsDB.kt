package megan.wamboi.billsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import megan.wamboi.billsapp.model.Bill
import megan.wamboi.billsapp.model.UpcomingBill
import megan.wamboi.billsapp.model.UpcomingBillsDao

@Database(entities =[Bill::class,UpcomingBill::class], version = 3)
abstract class BillsDB:RoomDatabase() {
    abstract fun billsdao():BillsDao

    abstract fun upcomingBillsDao():UpcomingBillsDao
    companion object{
        var database:BillsDB? =null
        fun getDatabase(context: Context):BillsDB{
            if (database==null){
                database=Room
                    .databaseBuilder(context,BillsDB::class.java,"BillsDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillsDB
        }
    }
}