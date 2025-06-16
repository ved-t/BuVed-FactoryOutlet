package com.example.buved.presentation.event.home

import com.example.buved.presentation.Destination

sealed class NavigationEvent {
    data class Navigate(val destination: Destination): NavigationEvent()
    data object PopBackStack: NavigationEvent()
}