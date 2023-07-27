package megan.wamboi.billsapp.model

import com.google.gson.annotations.SerializedName

data class User(
   @SerializedName("phone_number")var phoneNumber:String,
   @SerializedName("first_name")var firstName:String,
   @SerializedName("last_name")var lastName:String,
   @SerializedName("user_id")var userId:String,
   var email:String
)

//data class User(
//   var phone_Number:String,
//   var first_Name: String,
//   var last_Name:String,
//   var user_id: String,
//   var email:String
//)
