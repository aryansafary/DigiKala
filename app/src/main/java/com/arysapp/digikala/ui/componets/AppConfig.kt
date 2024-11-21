package com.arysapp.digikala.ui.componets

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.arysapp.digikala.util.Constants.USER_LANGUAGE
import com.arysapp.digikala.viewmodel.DataStoreViewModel

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel()
){
getDataStoreVariable(dataStore)
}

fun getDataStoreVariable(dataStore:DataStoreViewModel){
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)
}