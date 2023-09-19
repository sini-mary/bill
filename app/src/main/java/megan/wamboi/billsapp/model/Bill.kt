package megan.wamboi.billsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo.Index

@Entity(tableName = "Bills",
    indices = [androidx.room.Index(value =["name"], unique = true)])
data class Bill(
    @PrimaryKey val billid:String,
    var name:String,
    var amount: Double,
    var frequency: String,
    var dueDate: String,

)
