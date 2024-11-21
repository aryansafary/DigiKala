package com.arysapp.digikala.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFed1b34)
val Purple500 = Color(0xFFCE3C4D)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val Colors.splashBg: Color
    @Composable
    get() = Color(0xFFed1b34)


val Colors.selectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFF43474C) else Color(0xFFCFD4DA)

val Colors.unSelectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFFA4A1A1) else Color(0xFF575A5E)


val Colors.searchBarBg: Color
    @Composable
    get() = if (isLight) Color(0xFFF1F0EE) else Color(0xFF303235)


val Colors.darkText: Color
    @Composable
    get() = if (isLight) Color(0xFF414244) else Color(0xFFD8D8D8)


val Colors.amber: Color
    @Composable
    get() =  Color(0xffFFBF00)


val Colors.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)
