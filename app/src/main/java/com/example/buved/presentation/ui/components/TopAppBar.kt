package com.example.buved.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(){
    TopAppBar(
        title = { Text(text = "Online Shop") },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
            }

            IconButton(onClick = { }) {
                Icon(Icons.Filled.MailOutline, contentDescription = "Mail Icon")
            }

            IconButton(onClick = { }) {
                Icon(Icons.Outlined.AddAlert, contentDescription = "Alert Icon")
            }
        }
    )
}