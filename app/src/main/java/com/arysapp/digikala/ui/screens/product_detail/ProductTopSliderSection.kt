package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.arysapp.digikala.data.model.product_detail.SliderImage
import com.arysapp.digikala.ui.theme.LocalShape
import com.arysapp.digikala.ui.theme.LocalSpacing
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductTopSliderSection(
    sliderList: List<SliderImage>
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val pagerState = rememberPagerState()
            var imageUrl by remember {
                mutableStateOf("")
            }

            Box() {

                HorizontalPager(
                    count = sliderList.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { index ->
                    imageUrl = sliderList[index].image
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        GlideImage(
                            model = imageUrl,
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