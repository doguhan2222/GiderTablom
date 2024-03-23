package com.sedogapps.gidertablom.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
@Composable
fun CircularImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    imageSize: Int = 120 // Resim boyutu

) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(imageSize.dp) // Resim boyutunu belirle
            .clip(shape = CircleShape) // Daire şeklinde kırpmayı uygula
    )
}