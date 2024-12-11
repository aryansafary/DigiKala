@file:Suppress("DEPRECATION")

package com.arysapp.digikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arysapp.digikala.R
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.*

@Composable
fun ProductDescriptionSection(
    navController: NavHostController,
    description: String,
    technicalFeatures: String,
) {


    var isDescription by remember { mutableStateOf(true) }
    if (description.isBlank()) {
        isDescription = false
    }

    var isTechnicalFeatures by remember { mutableStateOf(true) }
    if (technicalFeatures == "null") {
        isTechnicalFeatures = false
    }




    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Text(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small),
        text = stringResource(id = R.string.product_desc),
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )





    if (isTechnicalFeatures) {

        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        Screen.ProductTechnicalFeatures.withArgs(
                            technicalFeatures
                        )
                    )
                }
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.technical_specifications),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }
    }



    if (isDescription) {
        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ProductDescription.withArgs(description))
                }
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.product_introduce),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }
    }




    Row(
        modifier = Modifier
            .padding(
                MaterialTheme.spacing.semiMedium,
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.product_desc_feedback),
            style = MaterialTheme.typography.extraSmall,
            color = MaterialTheme.colors.unSelectedBottomBar,
        )

        Image(
            painter = painterResource(id = R.drawable.info),
            modifier = Modifier
                .size(20.dp), contentDescription = ""
        )
    }
}