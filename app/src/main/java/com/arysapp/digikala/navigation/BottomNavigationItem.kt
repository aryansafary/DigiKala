package com.arysapp.digikala.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
  val name: String,
  val route: String,
  val selectedIcon: Painter ,
  val deselectedIcon: Painter ,

)
