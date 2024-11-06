package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import com.arysapp.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {

}