package com.robert.challenge.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle


@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.primary,
    fontSize : TextStyle = MaterialTheme.typography.bodyLarge,
    style : TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Text(text = text,
        modifier = modifier
            .semantics {
                this.contentDescription = text
            },
        fontSize = fontSize.fontSize,
        color = color,
        style = style,
        )
}
