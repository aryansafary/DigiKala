package com.arysapp.digikala.ui.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arysapp.digikala.MainActivity
import com.arysapp.digikala.util.Constants.ENGLISH_LANGUAGE
import com.arysapp.digikala.util.Constants.PERSIAN_LANGUAGE
import com.arysapp.digikala.viewmodel.DataStoreViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    datastore: DataStoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val activity = LocalContext.current as Activity
        Button(onClick = {
            changeLanguage(PERSIAN_LANGUAGE, datastore, activity)
        }) {
            Text(text = "Persian Language")
        }
        Button(onClick = {
            changeLanguage(ENGLISH_LANGUAGE, datastore, activity)
        }) {
            Text(text = "English Language")
        }
    }
}

private fun changeLanguage(language: String, datastore: DataStoreViewModel, activity: Activity) {
    datastore.saveUserLanguage(language)
    activity.apply {
        finish()
        startActivity(Intent(activity, MainActivity::class.java))
    }
}