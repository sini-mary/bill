package megan.wamboi.billsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import megan.wamboi.billsapp.model.LoginRequest
import megan.wamboi.billsapp.model.LoginResponse
import megan.wamboi.billsapp.repository.LoginRepository

class LoginViewModel : ViewModel(){
    val userRepo = LoginRepository()
    val logLiveData = MutableLiveData<LoginResponse>()
    var errorLiveData = MutableLiveData<String>()


    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch{
            val response = userRepo.loginUser(loginRequest)
            if (response.isSuccessful){
                logLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}