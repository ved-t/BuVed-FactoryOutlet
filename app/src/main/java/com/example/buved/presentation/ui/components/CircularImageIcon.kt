package com.example.buved.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.buved.R

@Composable
fun CircularImageIcon(
    image: Int,
    imageDescription: String,
    size: Dp = 75.dp,
    onClick:() -> Unit = {}
){
    Image(
        painter = painterResource(image),
        contentDescription = imageDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
    )
}

@Composable
@Preview
fun CircularImageIconPreview(){
    CircularImageIcon(R.drawable.twitter_logo, "Face Book Logo")
}