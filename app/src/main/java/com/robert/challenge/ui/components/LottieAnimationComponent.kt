package com.robert.challenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.robert.challenge.R

@Composable
fun LottieAnimationComponent(modifier: Modifier, drawableRes: Int, play: Boolean) {
    // Load the Lottie animation from the raw resource
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(drawableRes)
        )
        var isPlaying by remember {
            mutableStateOf(play)
        }
        val progress by animateLottieCompositionAsState(
            composition = composition,
            isPlaying = play,
            iterations = LottieConstants.IterateForever,
            speed = 1.0f
        )
        LaunchedEffect(key1 = progress) {
            if (progress == 0f) {
                isPlaying = true
            }
            if (progress == 1f) {
                isPlaying = false
            }
        }

        LottieAnimation(
            composition = composition,
            modifier = modifier.size(200.dp)
                .clickable {
                    isPlaying = isPlaying
                },
            //iterations = LottieConstants.IterateForever
            progress = {
                progress
            }
        )
    }
}

@Preview
@Composable
fun LottieAnimationComponentPreview() {
    LottieAnimationComponent(modifier = Modifier, drawableRes = R.raw.animatedloading, play = true)
}