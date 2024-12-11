package com.arysapp.digikala.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arysapp.digikala.R
import com.arysapp.digikala.data.model.prfile.FavItem
import com.arysapp.digikala.ui.theme.DigikalaDarkRed
import com.arysapp.digikala.ui.theme.digikalaRed
import com.arysapp.digikala.ui.theme.font_standard
import com.arysapp.digikala.ui.theme.roundedShape
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.DigitHelper
import com.arysapp.digikala.viewmodel.FavoriteListViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteListScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            FavoriteListTopAppBar(navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            FavoriteListSection(navController)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteListSection(
    navController: NavHostController,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {

    val allFavoriteItems by viewModel.allFavoriteItems.collectAsState(emptyList<FavItem>())


    val coroutineScope = rememberCoroutineScope()

    var selectedItem by remember {
        mutableStateOf(FavItem("" , 1,"" , "" , 1 , "" , 1.0))
    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState ,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))
                Text(
                    text = stringResource(id = R.string.sure_to_remove_fav_item),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.biggerSmall)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ){
                    OutlinedButton(
                        onClick = {
                            viewModel.removeFavoriteItem(selectedItem)
                            coroutineScope.launch {
                                if (modalSheetState.isVisible){
                                    modalSheetState.hide()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.digikalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                        ){
                        Text(
                            text = stringResource(id = R.string.remove_fav_item),
                            color = MaterialTheme.colors.DigikalaDarkRed,
                            style = MaterialTheme.typography.h3

                        )
                    }

                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible){
                                    modalSheetState.hide()
                                }
                            }

                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.digikalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ){
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colors.DigikalaDarkRed,
                            style = MaterialTheme.typography.h3

                        )
                    }
                }
            }
        }
    ){
        Column {
            CountFavoriteSection(allFavoriteItems.size)

            LazyColumn(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxSize()
            ) {
                if (allFavoriteItems.isEmpty()) {
                    item { EmptyFavoriteListContent() }
                } else {
                    items(allFavoriteItems) { favItem ->
                        FavoriteItemCard(
                            navController,
                            favItem,
                            coroutineScope,
                            modalSheetState
                        ){
                            selectedItem = it
                        }
                    }
                }
            }

        }
    }




}


@Composable
fun EmptyFavoriteListContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.no_fav_item
            ),
            contentScale = ContentScale.Fit,
            contentDescription = "",
            modifier = Modifier
                .height(600.dp)
                .width(500.dp)
        )
    }
}

@Composable
fun CountFavoriteSection(itemCount: Int) {
    Row(
        Modifier.padding(
            top = MaterialTheme.spacing.biggerSmall,
            bottom = MaterialTheme.spacing.biggerMedium
        )
    ) {
        Text(
            text = stringResource(id = R.string.fav_products),
            color = Color.Gray,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.extraSmall)
                .weight(0.9f)
        )
        Text(
            text = "${DigitHelper.digitByLocate(itemCount.toString())} ${stringResource(id = R.string.product)}",
            color = Color.Gray,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(0.1f)
        )
    }
}

@Composable
fun FavoriteListTopAppBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Color.White,
        modifier = Modifier.height(70.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "arrow back",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Text(
                    text = stringResource(id = R.string.my_fav_list),
                    color = Color.DarkGray,
                    fontFamily = font_standard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(0.8f)
                )
            }
        }
    }
}