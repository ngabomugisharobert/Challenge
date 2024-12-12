package com.robert.challenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.robert.challenge.R


@Composable
fun ImageFromUrl(modifier: Modifier = Modifier, url: String, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .scale(coil.size.Scale.FIT)
            .build(),
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.unnamed),
    )
}