package com.example.buved.presentation.event.home

sealed class NavigationEvent {
    data class Navigate(val id: String): NavigationEvent()
    data object PopBackStack: NavigationEvent()
}