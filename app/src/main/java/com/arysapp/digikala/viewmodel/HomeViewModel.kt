package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arysapp.digikala.data.model.home.AmazingItem
import com.arysapp.digikala.data.model.home.Slider
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {
val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())


    suspend fun getAllDataFromServer(){
       viewModelScope.launch{
           // Fire and Forget-->
           launch{
           slider.emit(repository.getSlider())
           }
           launch{
           amazingItems.emit(repository.getAmazing())
           }
           launch {
               superMarketItems.emit(repository.getAmazingSuperMarketItems())
           }



       }
   }




}