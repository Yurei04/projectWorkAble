package com.example.projectworkable.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectworkable.ui.components.BlogCard
import com.example.projectworkable.R // 💡 Required for all R.drawable references

// 💡 Data Model reverted to use imageRes (Int)
data class BlogSource(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int, // 💡 Changed back to Resource ID
    val tag: String
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BlogScreen(
    posts: List<BlogSource> = sampleBlogPosts(),
    onOpenPost: (Int) -> Unit
) {
    // ... (Animation and Column setup remains the same) ...

    // Logo image is a local resource
    Image(
        painter = painterResource(id = R.drawable.ic_accessibility),
        contentDescription = "WorkAble Logo",
        modifier = Modifier.size(80.dp),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )

    // ... (rest of the header text) ...

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(posts) { post ->
            BlogCard(
                title = post.title,
                description = post.description,
                imageRes = post.imageRes, // 💡 Pass the Resource ID
                tag = post.tag,
                onClick = { onOpenPost(post.id) }
            )
        }
    }
}

/** ⬇️ Sample data using placeholder R.drawable references */
fun sampleBlogPosts(): List<BlogSource> = listOf(
    BlogSource(
        id = 1,
        title = "Navigating Discrimination in the Interview Process",
        description = "Legal steps and practical advice for challenging unfair questions or treatment during job interviews.",
        imageRes = R.drawable.ic_temporary, // 💡 Placeholder resource
        tag = "Advocacy"
    ),
    BlogSource(
        id = 2,
        title = "What is 'Reasonable Accommodation' and How to Ask For It",
        description = "A detailed guide on your rights to workplace adjustments and how to professionally document your request.",
        imageRes = R.drawable.ic_temporary,
        tag = "Rights"
    ),
    BlogSource(
        id = 3,
        title = "Salary Gap: How to Ensure You Get Paid Fairly",
        description = "Tactics and resources to benchmark your worth and negotiate a salary that reflects your skill, not your disability status.",
        imageRes = R.drawable.ic_temporary,
        tag = "Career"
    ),
    BlogSource(
        id = 4,
        title = "Microaggressions at Work: A Guide to Responding",
        description = "Tips for identifying, deflecting, and reporting subtle but harmful bias in the professional environment.",
        imageRes = R.drawable.ic_temporary,
        tag = "Stories"
    ),
    BlogSource(
        id = 5,
        title = "Creating an Inclusive WFH Setup for Chronic Illness",
        description = "Tips for ergonomic chairs, adaptive equipment, and managing energy limitations when working from home.",
        imageRes = R.drawable.ic_temporary,
        tag = "WFH"
    ),
    BlogSource(
        id = 6,
        title = "The Power of Disclosure: When and How to Talk to HR",
        description = "A strategic timeline on deciding whether to disclose your disability and how to prepare documentation for accommodation.",
        imageRes = R.drawable.ic_temporary,
        tag = "Advocacy"
    )
)