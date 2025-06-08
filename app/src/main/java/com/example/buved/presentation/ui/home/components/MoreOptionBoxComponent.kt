package com.example.buved.presentation.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buved.presentation.FilterType
import com.example.buved.presentation.event.home.HomeUiEvent
import com.example.buved.presentation.viewmodel.home.HomeViewModel

@Composable
fun MoreOptions(
    viewModel: HomeViewModel = hiltViewModel()
){
    val uiState by viewModel.homeUiState.collectAsState()

    Box{
        IconButton(
            onClick = {
                viewModel.onHomeEvent(HomeUiEvent.onFilterClick)
            }
        ) {
            Icon(Icons.Outlined.FilterList, contentDescription = "Filter Icon")
        }

        DropdownMenu(
            expanded = uiState.moreOptionsExpanded,
            onDismissRequest = {
                viewModel.onHomeEvent(HomeUiEvent.onFilterClick)
            },
            offset = DpOffset(x = 0.dp, y = 0.dp)
        ) {
            FilterType.entries.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = type.filterType,
                            fontSize = 12.sp
                        )
                    },
                    onClick = {
                        viewModel.onHomeEvent(HomeUiEvent.onFilter(type))
                        viewModel.onHomeEvent(HomeUiEvent.onFilterClick)
                    }
                )
            }
        }
    }
}