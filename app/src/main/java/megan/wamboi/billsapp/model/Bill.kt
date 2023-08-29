package megan.wamboi.billsapp.model

import android.widget.EditText
import android.widget.SpinnerAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Bills")
data class Bill(
    @PrimaryKey val billid:String,
    var name:String,
    var amount: String,
    var frequency: SpinnerAdapter,
    var dueDate: EditText
)
