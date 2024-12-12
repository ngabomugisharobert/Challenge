package com.robert.challenge.ui.screen.details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.robert.challenge.data.model.MediaModel
import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.ui.components.CustomText
import com.robert.challenge.ui.components.ImageCard
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
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            this.contentDescription = "Back Button"
                            Role.Button
                        },
                    onClick = {
                        navController.popBackStack()
                    },
                ){
                    Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Back Button")
                }

                CustomText(
                    text = "Details",
                    modifier = Modifier.padding(10.dp),
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Box(modifier = modifier.fillMaxSize()) {
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
                    Toast.makeText(LocalContext.current, "No photo selected", Toast.LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun PhotoInfo(details: Map<String, String>, selectedPhoto: PhotoModel) {
    Column {
        Row {
            CustomText(text = "Size: ", modifier = Modifier.padding(10.dp))
            CustomText(
                text = " ${details["width"] ?: ""}  x ${details["height"] ?: ""}",
                modifier = Modifier.padding(10.dp)
            )
            Log.i("DetailScreen", "Details: $details")
        }
        CustomText(text = selectedPhoto.title, modifier = Modifier.padding(10.dp))
        CustomText(
            text = "Description: ${details["description"] ?: ""}",
            modifier = Modifier.padding(10.dp)
        )
        CustomText(text = selectedPhoto.author, modifier = Modifier.padding(10.dp))
        CustomText(
            text = "Published: ${details["published"] ?: ""}",
            modifier = Modifier.padding(10.dp)
        )

    }
}

