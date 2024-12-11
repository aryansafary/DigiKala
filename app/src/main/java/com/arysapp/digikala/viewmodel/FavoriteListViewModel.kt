package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.arysapp.digikala.data.model.prfile.FavItem
import com.arysapp.digikala.repository.FavoriteListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val repository: FavoriteListRepository
) : ViewModel() {

    val allFavoriteItems: Flow<List<FavItem>> = repository.allFavoriteItems

    fun addFavoriteItem(favItem: FavItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavoriteItem(favItem)
        }
    }

    fun removeFavoriteItem(favItem: FavItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavoriteItem(favItem)
        }
    }

    fun clearFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearFavoriteList()
        }
    }

    fun isFavoriteItemExist(itemId: String): Flow<Boolean> =
        repository.isFavoriteItemExist(itemId)


}