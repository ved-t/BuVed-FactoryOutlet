package com.example.buved.presentation.ui.onboarding.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    size: Dp = 75.dp
){
    Image(
        painter = painterResource(image),
        contentDescription = imageDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    )
}

@Composable
@Preview
fun CircularImageIconPreview(){
    CircularImageIcon(R.drawable.twitter_logo, "Face Book Logo")
}