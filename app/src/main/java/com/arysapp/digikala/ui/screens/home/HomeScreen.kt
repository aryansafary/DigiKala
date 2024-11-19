package com.arysapp.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController = navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }
    SwipeRefreshSection(
        viewModel = viewModel,
        navController = navController
    )

}

@Composable
fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavHostController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = swipeRefreshState,
        indicatorAlignment = Alignment.TopCenter,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel)
                Log.e("Refresh", "Home: Refreshing ")
            }
        }
    ) {
        LazyColumn(
            modifier =
            Modifier.fillMaxSize()
        ) {
            item { SearchBarSection() }
            item { TopSliderSection() }
        }
    }
}

private suspend fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getSlider()
}