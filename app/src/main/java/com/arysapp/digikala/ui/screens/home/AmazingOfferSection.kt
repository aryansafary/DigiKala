package com.arysapp.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.arysapp.digikala.data.model.home.AmazingItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.arysapp.digikala.R
import com.arysapp.digikala.ui.theme.DigikalaLightRed

@Composable
fun AmazingOfferSection(
viewModel: HomeViewModel = hiltViewModel()
){
var amazingOfferList by remember{
    mutableStateOf<List<AmazingItem>>(emptyList())
}
    var loading by remember{
        mutableStateOf(false)
    }
    val amazingOfferResult by viewModel.amazingItems.collectAsState()
    when(amazingOfferResult){
        is NetworkResult.Success->{
            amazingOfferList = amazingOfferResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error->{
            loading = false
            Log.e("Error","Error Message : ${amazingOfferResult.message}")
        }
        is NetworkResult.Loading->{
            loading = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.DigikalaLightRed)) {

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightRed)) {

            item {
                AmazingOfferCard(R.drawable.amazings, R.drawable.box)
            }
            items(amazingOfferList) {item->
            AmazingItem(item)
            }
            item {
                AmazingShowMoreItem()
            }

        }
    }

    }