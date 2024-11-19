package com.arysapp.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arysapp.digikala.data.model.home.Slider
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.ui.theme.LocalShape
import com.arysapp.digikala.ui.theme.LocalSpacing
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopSliderSection(viewModel: HomeViewModel = hiltViewModel()) {
    var loading by remember { mutableStateOf(false) }
    var sliderList by remember { mutableStateOf<List<Slider>>(emptyList()) }
    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            Log.e("Message", "TopSlider: Success Message ${sliderList[0].url}")
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("Error", "TopSlider: Error Message ${sliderResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    horizontal = LocalSpacing.current.extraSmall,
                    vertical = LocalSpacing.current.small
                )
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }
            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { index ->

                imageUrl = sliderList[index].image
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    //Using Glide
                    GlideImage(
                        model = imageUrl,
                        contentDescription = "Test",
                        modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .clip(LocalShape.current.medium)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

//    Using Coil3
//                    AsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(imageUrl)
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = "Image Slider",
//                                modifier = Modifier
//                                . padding (LocalSpacing.current.small)
//                            .clip(LocalShape.current.medium)
//                            .fillMaxSize(),
//                        contentScale = ContentScale.FillBounds
//                    )
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(LocalSpacing.current.semiLarge),
                        activeColor = Color.Black,
                        inactiveColor = Color.White,
                        indicatorShape = CircleShape,
                        indicatorWidth = LocalSpacing.current.small,
                        indicatorHeight = LocalSpacing.current.small,

                        )
                }

            }
            LaunchedEffect(pagerState.currentPage) {
                delay(6000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition >= sliderList.size) newPosition = 0
                pagerState.scrollToPage(newPosition)
            }
        }
    }
}

