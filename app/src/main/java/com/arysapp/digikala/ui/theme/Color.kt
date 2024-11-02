package com.arysapp.digikala.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

enum class  CustomTheme{
    LIGHT , DARK
}
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val red = Color(0xFFEF3F3E)
val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)
@Stable
class CustomColor(
    backgroundColor: Color,
    textColor: Color,
    mainColor: Color
){
   var backgroundColor by mutableStateOf(backgroundColor)
    private set

    var textColor by mutableStateOf(textColor)
        private set

    var mainColor by mutableStateOf(mainColor)
        private set

    fun updateColor(colors: CustomColor){
      this.backgroundColor = colors.backgroundColor
      this.textColor = colors.textColor
      this.mainColor = colors.mainColor
    }

    fun initColor()= CustomColor(backgroundColor, textColor, mainColor)
}
