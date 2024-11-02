package com.arysapp.digikala.ui.theme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
private val CustomDarkTheme = CustomColor(
    backgroundColor = black ,
    textColor = white ,
    mainColor = red
)
private val CustomLightTheme = CustomColor(
    backgroundColor = white ,
    textColor = black ,
    mainColor = red
)
private val LocalColorsProvider = staticCompositionLocalOf {
    CustomLightTheme
}

@Composable
private fun CustomLocalProvider(
    colors: CustomColor,
    content:@Composable () -> Unit
) {
    val colorPalette  = remember {
        colors.initColor()
    }
    colorPalette.updateColor(colors)
    CompositionLocalProvider(
        value = LocalColorsProvider provides colorPalette,
        content = content
        )
}

private val CustomTheme.colors : Pair<ColorScheme, CustomColor>
    get() = when(this){
      CustomTheme.DARK -> darkColorScheme()   to CustomDarkTheme
      CustomTheme.LIGHT -> lightColorScheme() to CustomLightTheme
    }

object CustomThemeManager{
 val colors : CustomColor
    @Composable
    get() = LocalColorsProvider.current
    val customTheme by mutableStateOf(CustomTheme.LIGHT)
    fun isSystemInDarkTheme():Boolean{
    return customTheme== CustomTheme.DARK
    }


}

@Composable
fun DigiKalaTheme(
    customTheme: CustomTheme = CustomThemeManager.customTheme,
    content: @Composable () -> Unit
) {
    val(colorPalette , myColor)=customTheme.colors
    CustomLocalProvider(colors = myColor) {
        MaterialTheme(
            colorScheme = colorPalette,
            typography = Typography,
            content = content
        )
    }


}