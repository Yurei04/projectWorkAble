package com.example.projectworkable.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BlogCard(
    title: String,
    description: String,
    image: Painter,
    onClick: () -> Unit, // We can keep this for future navigation
    tag: String
) {
    // 1. State to control dialog visibility
    var showDialog by remember { mutableStateOf(false) }

    // When the card is clicked, we'll set the state to true
    BlogItemBox(
        title = title,
        description = description,
        image = {
            Image(
                painter = image,
                contentDescription = title,
                contentScale = ContentScale.Crop, // Makes the image fit well
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape) // Makes the image circular
            )
        },
        onClick = { showDialog = true }, // Updated onClick to show the dialog
        tag = tag
    )

    // 3. The AlertDialog that appears when showDialog is true
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, // Hide dialog when clicking outside
            title = {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
            },
            text = {
                Text(text = description, style = MaterialTheme.typography.bodyLarge)
            },
            confirmButton = {
                TextButton(
                    onClick = { showDialog = false } // Hide dialog on button press
                ) {
                    Text("Close")
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun BlogItemBox(
    title: String,
    description: String,
    image: @Composable () -> Unit,
    onClick: () -> Unit,
    tag: String
) {
    // 2. Implemented UI for the card item
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick), // Make the entire card clickable
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image Composable (e.g., Company Logo)
            image()

            Spacer(modifier = Modifier.width(16.dp))

            // Column for Title and Description
            Column(
                modifier = Modifier.weight(1f) // Takes up remaining space
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2 // Limit description lines for a clean look
                )
            }
        }
    }
}
