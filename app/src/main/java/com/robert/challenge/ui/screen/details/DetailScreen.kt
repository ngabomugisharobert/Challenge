package com.robert.challenge.ui.screen.details

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.ui.components.CustomText
import com.robert.challenge.ui.components.ImageCard
import com.robert.challenge.ui.screen.Screen
import com.robert.challenge.utils.ExtractInfo
import com.robert.challenge.viewmodel.FlickrViewModel


@Composable
fun DetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FlickrViewModel
) {

    val selectedPhoto = viewModel.selectedPhoto.value

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            this.contentDescription = "Back Button"
                            Role.Button
                        },
                    onClick = {
                        navController.popBackStack()
                        viewModel.clearSelectedPhoto()
                    },
                ) {
                    Icon(
                        Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back Button",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                CustomText(
                    text = "Details",
                    modifier = Modifier.padding(10.dp),
                )
                if (selectedPhoto != null) {
                    ShareButton(selectedPhoto = selectedPhoto)
                }
            }
        }
    ) { innerPadding ->

//        check if screen is in landscape mode or portrait mode
        if (LocalContext.current.resources.configuration.orientation == 1) {
            PortraitScreen(
                selectedPhoto = selectedPhoto,
                navController = navController,
                modifier = modifier,
                innerPadding = innerPadding.calculateTopPadding()
            )
        } else {
            LandscapeScreen(
                selectedPhoto = selectedPhoto,
                navController = navController,
                modifier = modifier,
                innerPadding = innerPadding.calculateTopPadding()
            )
        }
    }
}


@Composable
fun PortraitScreen(
    selectedPhoto: PhotoModel?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    innerPadding: Dp
) {

    Box(
        modifier = modifier
            .padding(top = innerPadding)
            .animateContentSize()
            .fillMaxSize()
    ) {
        if (selectedPhoto != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
            ) {
                ImageCard(
                    photo = selectedPhoto, modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                val details = ExtractInfo.extractPhotoDetails(selectedPhoto)


                PhotoInfo(details = details, selectedPhoto = selectedPhoto)


            }
        } else {
            navController.navigate(Screen.Home.route)
        }
    }
}

@Composable
fun LandscapeScreen(
    selectedPhoto: PhotoModel?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    innerPadding: Dp,
) {

    Box(
        modifier = modifier
            .padding(0.dp)
            .fillMaxSize()
    ) {
        if (selectedPhoto != null) {
            val details = ExtractInfo.extractPhotoDetails(selectedPhoto)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageCard(
                    photo = selectedPhoto, modifier = Modifier
                        .fillMaxHeight()
                        .width(400.dp)
                )
                PhotoInfo(details = details, selectedPhoto = selectedPhoto)

            }
        } else {
            Toast.makeText(LocalContext.current, "No photo selected", Toast.LENGTH_SHORT)
                .show()
            navController.popBackStack()
        }
    }
}


@Composable
fun PhotoInfo(details: Map<String, String>, selectedPhoto: PhotoModel) {
    Column {
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Size: ", modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.tertiary
                    )
                    .background(MaterialTheme.colorScheme.tertiary),
            )
            {
                CustomText(
                    text = " ${details["width"] ?: "n/a"}  x ${details["height"] ?: "n/a"}",
                    modifier = Modifier
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
                Log.i("DetailScreen", "Details: $details")
            }
        }

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Title :")
            CustomText(text = selectedPhoto.title, modifier = Modifier.padding(10.dp))
        }

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Title :")
            CustomText(
                text = "Description: ${details["description"] ?: "no description"}",
                modifier = Modifier.padding(10.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Author :")
            CustomText(
                text = "${details["author"] ?: " Unknown"}",
                modifier = Modifier.padding(10.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Published: ")
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.tertiary
                    )
                    .background(MaterialTheme.colorScheme.tertiary),
            )
            {
                CustomText(
                    text = "${details["published"] ?: " Unknown"}",
                    modifier = Modifier
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

@Composable
fun ShareButton(selectedPhoto: PhotoModel) {
    val context = LocalContext.current
    IconButton(onClick = {
        val shareText = """
                                Title: ${selectedPhoto.title}
                                Description: ${selectedPhoto.description ?: "No description"}
                                Image URL: ${selectedPhoto.link} 
                            """.trimIndent()
        val imageUri: Uri = Uri.parse(selectedPhoto.link)

        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/*"
        }
        context.startActivity(Intent.createChooser(intent, "Share Image and Metadata"))
    }) {
        Icon(
            Icons.Filled.Share,
            contentDescription = "Share Image and Metadata",
        )
    }
}

