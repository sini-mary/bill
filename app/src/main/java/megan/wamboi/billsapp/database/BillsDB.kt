package megan.wamboi.billsapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class BillsDB:RoomDatabase() {
    abstract fun billsdao():BillsDao
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