package megan.wamboi.billsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.37.106.218")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
//Creates an instance of the retrofit
    fun <T> buildClient(apiInterface: Class<T>): T{
        return retrofit.create(apiInterface)
    }
}