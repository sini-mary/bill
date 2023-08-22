package megan.wamboi.billsapp.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message:String,
    var access_token:String,
    var user_id:String
)