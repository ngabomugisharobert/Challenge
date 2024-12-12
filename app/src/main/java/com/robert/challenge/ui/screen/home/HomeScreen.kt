package com.robert.challenge.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.robert.challenge.R
import com.robert.challenge.data.remote.ApiResult
import com.robert.challenge.ui.components.CustomSearchBar
import com.robert.challenge.ui.components.ImageCard
import com.robert.challenge.ui.components.LottieAnimationComponent
import com.robert.challenge.ui.screen.Screen
import com.robert.challenge.viewmodel.FlickrViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    flickrViewModel: FlickrViewModel
) {

//    state
    val query = rememberSaveable { mutableStateOf("") }
    val apiResponse by flickrViewModel.photos.collectAsStateWithLifecycle()


    Box(modifier = modifier)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            CustomSearchBar(query = query, onQueryChange = { searchString ->
                query.value = searchString
                flickrViewModel.searchPhotos(query.value)
            })


            when (apiResponse) {
                is ApiResult.Error -> {
                    Text("Error: ${(apiResponse as ApiResult.Error).exception.message}")
                }

                is ApiResult.Loading -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LottieAnimationComponent(modifier = Modifier, R.raw.animatedloading, true)
                    }
                }

                is ApiResult.Success -> {
                    val photos = (apiResponse as ApiResult.Success).data
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(120.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(photos) { photo ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        flickrViewModel.selectPhoto(photo)
                                        navController.navigate(Screen.Detail.route)
                                    }) {
                                ImageCard(
                                    photo = photo, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                )
                            }
                        }
                    }
                }

                ApiResult.Idle -> {
                    Box(modifier = Modifier) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            LottieAnimationComponent(modifier = Modifier, R.raw.animatedloading, false)
                        }
                    }
                }
            }

        }
    }
}
