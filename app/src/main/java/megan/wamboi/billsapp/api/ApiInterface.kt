package megan.wamboi.billsapp.api

import megan.wamboi.billsapp.model.LoginRequest
import megan.wamboi.billsapp.model.LoginResponse
import megan.wamboi.billsapp.model.RegisterRequest
import megan.wamboi.billsapp.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest:RegisterRequest): Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUsers(@Body loginRequest: LoginRequest):Response<LoginResponse>
}