package com.arysapp.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.arysapp.digikala.data.model.home.Slider
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.ui.components.OurLoading
import com.arysapp.digikala.ui.theme.LocalShape
import com.arysapp.digikala.ui.theme.LocalSpacing
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun TopSliderSection(viewModel: HomeViewModel = hiltViewModel()) {

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "Top Slider error : ${sliderResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    } else {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = LocalSpacing.current.extraSmall,
                        vertical = LocalSpacing.current.small
                    )
            ) {
                val pagerState = rememberPagerState()
                var imageUrl by remember {
                    mutableStateOf("")
                }

                Box {
                    HorizontalPager(
                        count = sliderList.size,
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { index ->
                        imageUrl = sliderList[index].image
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            GlideImage(
                                model =imageUrl ,
                                contentDescription = "", modifier = Modifier
                                    .padding(LocalSpacing.current.small)
                                    .clip(LocalShape.current.medium)
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )


                        }
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(LocalSpacing.current.semiLarge),
                        activeColor = Color.Black,
                        inactiveColor = Color.LightGray,
                        indicatorWidth = LocalSpacing.current.small,
                        indicatorHeight = LocalSpacing.current.small,
                        indicatorShape = CircleShape
                    )
                }


                LaunchedEffect(key1 = pagerState.currentPage) {
                    delay(6000)
                    var newPosition = pagerState.currentPage + 1
                    if (newPosition > sliderList.size - 1) newPosition = 0
                    pagerState.scrollToPage(newPosition)
                }


            }
        }
    }


}