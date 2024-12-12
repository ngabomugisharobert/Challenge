package com.robert.challenge.ui.screen

import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.utils.ScreenRoute


sealed class Screen(
    val route: String,
)
{
    object Home : Screen(ScreenRoute.HOME.name)
    object Detail : Screen(ScreenRoute.DETAILS.name)
}

