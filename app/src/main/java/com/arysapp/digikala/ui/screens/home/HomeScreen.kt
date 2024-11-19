package com.arysapp.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController){
Home(navController)
}
@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){
    Column(modifier =
    Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        val refreshScope = rememberCoroutineScope()
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
        SwipeRefresh(
            state = swipeRefreshState,
            indicatorAlignment = Alignment.TopCenter,
            onRefresh = {
                refreshScope.launch {
                    Log.e("Refresh", "Home: Refreshing ")
                }
            }
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 60.dp)
            ) {
                LaunchedEffect(true) {
               viewModel.getSlider()
                }
                TopSliderSection()
            }
        }
    }
        }