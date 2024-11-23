package com.arysapp.digikala.ui.screens.home
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arysapp.digikala.data.model.home.Slider
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.ui.theme.roundedCornerShape
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.viewmodel.HomeViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ProposalCardSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bannersList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val bannersResult by viewModel.banners.collectAsState()
    when (bannersResult) {
        is NetworkResult.Success -> {
            bannersList = bannersResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "Banners Section error : ${bannersResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .padding(MaterialTheme.spacing.small)
    ) {
       items(bannersList){item->
           ProposalCardItem(item)
       }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProposalCardItem(imgLink: Slider) {
    Card(
        shape = MaterialTheme.roundedCornerShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.small
            ),
    ) {

        GlideImage(
            model = imgLink.image,
            contentDescription = "Proposal Banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }
}

