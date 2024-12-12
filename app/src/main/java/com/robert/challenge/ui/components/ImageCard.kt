package com.robert.challenge.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.robert.challenge.data.model.PhotoModel

@SuppressLint("CheckResult")
@Composable
fun ImageCard(modifier: Modifier = Modifier, photo: PhotoModel) {

//    get context
    val context = LocalContext.current

    Box(modifier = modifier)
    {
        ImageFromUrl(
            url = photo.media.m,
            contentDescription = photo.title
        )
    }

}