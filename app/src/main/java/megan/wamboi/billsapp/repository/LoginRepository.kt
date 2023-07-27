package megan.wamboi.billsapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import megan.wamboi.billsapp.api.ApiClient
import megan.wamboi.billsapp.api.ApiInterface
import megan.wamboi.billsapp.model.LoginRequest
import megan.wamboi.billsapp.model.LoginResponse
import retrofit2.Response

class LoginRepository {
    val apiClients = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            apiClients.loginUsers(loginRequest)
        }

    }
}