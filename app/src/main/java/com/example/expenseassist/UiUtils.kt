package com.example.expenseassist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun Modifier.highlightOnClick(): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState() // Sửa lại cú pháp

    return this.then(
        Modifier
            .clickable(interactionSource = interactionSource, indication = null) {}
            .graphicsLayer {
                alpha = if (isPressed) 0.7f else 1f // Hiệu ứng mờ khi nhấn
            }
    )
}