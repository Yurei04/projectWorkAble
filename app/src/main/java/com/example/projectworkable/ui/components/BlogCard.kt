package com.example.projectworkable.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.projectworkable.R

/**
 * A composable that displays a single blog post in a card format.
 * It's designed to be clickable and visually distinct.
 *
 * @param title The main title of the blog post.
 * @param description A short summary of the blog post.
 * @param image The painter resource for the card's banner image.
 * @param tag A category tag (e.g., "Career", "Skills").
 * @param onClick A lambda function to be invoked when the card is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogCard(
    title: String,
    description: String,
    image: Painter,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // Box to stack the Image and the Tag
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Gives the image a consistent height
            ) {
                // Background Image
                Image(
                    painter = image,
                    contentDescription = title,
                    contentScale = ContentScale.Crop, // Crop ensures the image fills the space
                    modifier = Modifier.fillMaxSize()
                )

                // Tag, styled and placed at the top-start corner
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = tag,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Column for Title and Description
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3 // Prevent long descriptions from taking up too much space
                )
            }
        }
    }
}

/**
 * A preview function to visualize the BlogCard in Android Studio.
 * This helps in designing and iterating on the component without running the app.
 */
@Preview(showBackground = true, name = "Blog Card Preview")
@Composable
fun BlogCardPreview() {
    // You can wrap your preview in your app's theme for accurate representation
    // ProjectWorkAbleTheme {
    BlogCard(
        title = "How to Create an Accessible Resume",
        description = "Resume techniques and templates tailored for people with disabilities — simple steps to highlight your strengths.",
        image = painterResource(id = R.drawable.ic_temporary), // Make sure ic_temporary exists
        tag = "Career",
        onClick = {}
    )
    // }
}
