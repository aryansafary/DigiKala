package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.arysapp.digikala.data.model.address.AddAddressRequest
import com.arysapp.digikala.data.model.address.UserAddress
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.repository.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())

    val addNewAddressResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())



    fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

    fun setNewAddress(address: AddAddressRequest) {
        viewModelScope.launch {
            addNewAddressResponse.emit(repository.setNewAddress(address))
        }
    }

}