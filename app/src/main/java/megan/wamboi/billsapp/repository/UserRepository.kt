package megan.wamboi.billsapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import megan.wamboi.billsapp.api.ApiClient
import megan.wamboi.billsapp.api.ApiInterface
import megan.wamboi.billsapp.model.RegisterRequest
import megan.wamboi.billsapp.model.RegisterResponse
import retrofit2.Response

class UserRepository {
val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
}