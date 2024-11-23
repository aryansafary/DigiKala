package com.arysapp.digikala.ui.theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
data class RoundedCornerShapes(
    val extraSmall : RoundedCornerShape = RoundedCornerShape(4.dp),
    val small : RoundedCornerShape = RoundedCornerShape(8.dp),
    val biggerSmall : RoundedCornerShape = RoundedCornerShape(10.dp),
    val semiMedium : RoundedCornerShape = RoundedCornerShape(14.dp),
    val medium : RoundedCornerShape = RoundedCornerShape(16.dp),
    val biggerMedium : RoundedCornerShape = RoundedCornerShape(20.dp),
    val large : RoundedCornerShape = RoundedCornerShape(24.dp),
)
val LocalShape = compositionLocalOf{ RoundedCornerShapes() }

val MaterialTheme.roundedCornerShape: RoundedCornerShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShape.current