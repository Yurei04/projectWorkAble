package com.example.projectworkable.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A custom button for switching between tabs (e.g., Resources and Tools).
 *
 * @param text The text to display on the button.
 * @param isSelected Whether this tab is currently selected.
 * @param onClick The lambda to be invoked when the button is clicked.
 */
@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            // --- FIX APPLIED HERE ---
            // This clickable overload is required to prevent the crash on newer Compose versions.
            // It explicitly defines the interactionSource and the indication (ripple effect).
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true) // Provides the ripple effect
            )
            .padding(vertical = 10.dp, horizontal = 20.dp), // Adjusted padding for better look
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}

// --- PREVIEW ---
// Helps visualize the component in different states without running the app.

@Preview(name = "Tab Button Selected", showBackground = true)
@Composable
fun TabButtonSelectedPreview() {
    // You can wrap previews in your app's theme for consistency
    // ProjectWorkAbleTheme {
    TabButton(text = "Resources", isSelected = true, onClick = {})
    // }
}

@Preview(name = "Tab Button Unselected", showBackground = true)
@Composable
fun TabButtonUnselectedPreview() {
    // ProjectWorkAbleTheme {
    TabButton(text = "Tools", isSelected = false, onClick = {})
    // }
}
