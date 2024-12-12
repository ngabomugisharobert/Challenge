package com.robert.challenge.ui.components

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


@Composable
fun CustomSearchBar(
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    query: MutableState<String>,
) {
    val scope = rememberCoroutineScope()
    var timer by remember { mutableStateOf<Timer?>(null) }

    Box(modifier = modifier
        .fillMaxWidth()
        .background(
            color = colorScheme.error,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
        )) {
        OutlinedTextField(
            value = query.value,
            onValueChange = onQueryChange
//            {
//                query.value = it
//                timer?.cancel()
//                timer = Timer().apply {
//                    schedule(object : TimerTask() {
//                        override fun run() {
//                            scope.launch {
//                                onQueryChange(it)
//                            }
//                        }
//                    }, 500)
//                }
//            },
            ,label = { CustomText("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    this.contentDescription = "Search"
                }
                .background(
                    color = colorScheme.surface,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                ),
            singleLine = true,
            maxLines = 1,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun CustomSearchBarPreview() {
    CustomSearchBar(
        onQueryChange = {},
        query = androidx.compose.runtime.mutableStateOf(""))
}

