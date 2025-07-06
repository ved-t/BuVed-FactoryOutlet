package com.example.buved.presentation.ui.cart.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.buved.domain.model.Product
import com.example.buved.presentation.ui.components.LoadingIndicator
import com.example.buved.presentation.ui.components.disabledTouch

@Composable
fun CartProductItem(
    product: Product,
    price: String,
    itemQuantity: String,
    onFavourite: () -> Unit,
    onRemoveFavourite: () -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onNavigate: () -> Unit,
    isTouchDisabled: Boolean
){
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable {
                onNavigate()
            }
    ) {
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
        ){
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                loading = { LoadingIndicator() },
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(150.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Inside,
            )

            IconButton(
                onClick = {
                    if(!product.isFavourite) onFavourite() else onRemoveFavourite()
                    if(!product.isFavourite) Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show() else Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    if(!product.isFavourite) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                    contentDescription = "Favourite Icon"
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = product.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$$price",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .disabledTouch(isTouchDisabled)
            ){
                IconButton(
                    onClick = { if(!isTouchDisabled) onDecrement() },
                ) {
                    Icon(Icons.Outlined.Remove, contentDescription = "Decrement Item")
                }

                Text(text = itemQuantity, fontSize = 12.sp, fontWeight = FontWeight.Normal)

                IconButton(onClick = { if(!isTouchDisabled) onIncrement() }) {
                    Icon(Icons.Outlined.Add , contentDescription = "Increment Item")
                }
            }
        }
    }
}