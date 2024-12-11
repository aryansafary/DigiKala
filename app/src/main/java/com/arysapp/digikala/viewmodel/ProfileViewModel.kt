package com.arysapp.digikala.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.arysapp.digikala.data.model.checkout.OrderFullDetail
import com.arysapp.digikala.data.model.prfile.LoginRequest
import com.arysapp.digikala.data.model.prfile.LoginResponse
import com.arysapp.digikala.data.model.prfile.SetUserNameRequest
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.repository.ProfileRepository
import com.arysapp.digikala.ui.screens.profile.ProfileScreenState
import com.arysapp.digikala.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    //sharedViewModel
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    val setUserNameResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())



    val orderItems =
        MutableStateFlow<NetworkResult<List<OrderFullDetail>>>(NetworkResult.Loading())


    fun login() {
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }


    fun refreshToken(phone: String, password: String){
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone , password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun setUserName (newUserName: SetUserNameRequest){
        viewModelScope.launch {
            setUserNameResponse.emit(repository.setUserName(newUserName))
        }
    }

    fun getUserOrders() {
        viewModelScope.launch {
            orderItems.emit(repository.getUserOrders(Constants.USER_TOKEN))
        }
    }

}