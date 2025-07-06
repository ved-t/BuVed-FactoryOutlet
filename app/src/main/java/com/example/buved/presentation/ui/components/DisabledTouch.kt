package com.example.buved.presentation.ui.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.disabledTouch(disabled: Boolean = false) = this.then(
    if(disabled){
        this.pointerInput(Unit){
            awaitEachGesture {
                while (true){
                    val event = awaitPointerEvent()
                    event.changes.forEach{it.consume()}
                }
            }
        }
    }else{
        this
    }
)