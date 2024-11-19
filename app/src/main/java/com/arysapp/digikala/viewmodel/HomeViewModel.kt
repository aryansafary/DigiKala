package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    suspend fun getSlider() {
        viewModelScope.launch {
        slider.emit(repository.getSlider())
        }


    }

}