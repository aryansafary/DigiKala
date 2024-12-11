package com.arysapp.digikala.ui.screens.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arysapp.digikala.BuildConfig
import com.arysapp.digikala.MainActivity
import com.arysapp.digikala.R
import com.arysapp.digikala.navigation.Screen
import com.arysapp.digikala.ui.theme.digikalaRed
import com.arysapp.digikala.ui.theme.selectedBottomBar
import com.arysapp.digikala.ui.theme.semiDarkText
import com.arysapp.digikala.ui.theme.spacing
import com.arysapp.digikala.util.Constants
import com.arysapp.digikala.util.Constants.DIGI_BUG
import com.arysapp.digikala.util.Constants.DIGI_FAQ
import com.arysapp.digikala.util.Constants.DIGI_PRIVACY
import com.arysapp.digikala.util.Constants.DIGI_SCORE
import com.arysapp.digikala.util.Constants.DIGI_TERMS
import com.arysapp.digikala.util.Constants.DIGI_TURLEARN
import com.arysapp.digikala.viewmodel.BasketViewModel
import com.arysapp.digikala.viewmodel.DataStoreViewModel
import com.arysapp.digikala.viewmodel.ProfileViewModel

@Composable
fun SettingScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        //.verticalScroll(rememberScrollState())
    ) {
        SettingHeader(navController)

        SettingMenuSection(navController)

        Spacer(modifier = Modifier.weight(1f))

        SettingBranding()
    }
}

@Composable
fun SettingHeader(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.large, end = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.setting)
        )

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.Close, contentDescription = "Close",
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.small
                    ),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }
    }
}


@Composable
fun SettingBranding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(100.dp).
            padding(top = MaterialTheme.spacing.extraSmall),
            painter = painterResource(id = R.drawable.digi_red_english),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.version_app, BuildConfig.VERSION_NAME),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.semiDarkText,
            modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall)
        )
            Text(
                text = stringResource(id = R.string.arysapp_text),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall)
            )

    }
}

@Composable
fun SettingMenuSection(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.HelpCenter,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.repetitive_questions),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_FAQ}"
        )

    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PrivacyTip,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.privacy),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_PRIVACY}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.OtherHouses,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.terms_of_use),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TERMS}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Call,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.contact_us),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TURLEARN}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PestControl,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.error_report),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_BUG}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.StarRate,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.rating_to_digikal),
        isHaveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_SCORE}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Language,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.changing_lang),
        addCompose = { ChangeLanguage() },
        isHaveDivider = true
    ) {

    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Logout,
                contentDescription = "",
                tint = MaterialTheme.colors.digikalaRed
            )
        },
        text = stringResource(id = R.string.sign_out),
        color = MaterialTheme.colors.digikalaRed,
        addCompose = {},
        isHaveDivider = false
    ) {
        logOut(navController, dataStore, profileViewModel, basketViewModel)
    }

}

fun logOut(
    navController: NavHostController,
    dataStore: DataStoreViewModel,
    profileViewModel: ProfileViewModel,
    basketViewModel: BasketViewModel
) {
    basketViewModel.deleteAllItems()
    dataStore.apply {
        saveUserToken("null")
        saveUserId("null")
        saveUserPhoneNumber("null")
        saveUserPassword("null")
        saveUserName("null")
        saveUserAddressIndex("0")
    }
    Constants.USER_TOKEN = "null"
    profileViewModel.screenState = ProfileScreenState.LOGIN_STATE
    navController.navigate(Screen.Profile.route)
}

@Composable
fun ChangeLanguage(dataStore: DataStoreViewModel = hiltViewModel()) {
    val activity = LocalContext.current as Activity
    Row(verticalAlignment = Alignment.CenterVertically) {
        val lang = dataStore.getUserLanguage()
        val checkedState by remember { mutableStateOf(lang) }

        Text(text = stringResource(id = R.string.english))
        Switch(
            checked = (checkedState == "fa"),
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colors.digikalaRed
            ),
            onCheckedChange = {
                dataStore.saveUserLanguage(if (lang == "en") "fa" else "en")
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }

            },
            modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.extraSmall )
        )
        Text(text = stringResource(id = R.string.farsi))
    }
}