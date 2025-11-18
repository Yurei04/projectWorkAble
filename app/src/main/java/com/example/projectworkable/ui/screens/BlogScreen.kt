package com.example.projectworkable.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectworkable.R
import com.example.projectworkable.ui.components.BlogCard

// ⬇️ Correct data class – keep imageRes as Int since painterResource is inside composable.
data class BlogSource(
    val title: String,
    val description: String,
    val imageRes: Int,
    val tag: String
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BlogScreen(
    posts: List<BlogSource> = sampleBlogPosts(),
    onOpenPost: (BlogSource) -> Unit = {}
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(animationSpec = tween(600)) +
                    fadeIn(animationSpec = tween(600))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.ic_accessibility),
                    contentDescription = "WorkAble Logo",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "WorkAble Blog",
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Guides • Stories • Tips • Accessibility",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(posts) { post ->
                BlogCard(
                    title = post.title,
                    description = post.description,
                    image = painterResource(id = post.imageRes),
                    tag = post.tag,
                    onClick = { onOpenPost(post) }
                )
            }
        }
    }
}

/** ⬇️ Sample data (same as your original) */
fun sampleBlogPosts(): List<BlogSource> = listOf(
    BlogSource(
        title = "How to Create an Accessible Resume",
        description = "Resume techniques and templates tailored for people with disabilities — simple steps to highlight your strengths.",
        imageRes = R.drawable.ic_temporary,
        tag = "Career"
    ),
    BlogSource(
        title = "Top Inclusive Employers of 2025",
        description = "A curated list of companies leading the way in accessibility hiring and inclusive workplace design.",
        imageRes = R.drawable.ic_temporary,
        tag = "Jobs"
    ),
    BlogSource(
        title = "Learn 3 New Skills From Home",
        description = "Low-barrier, high-impact skills you can start practicing today to improve employability and confidence.",
        imageRes = R.drawable.ic_temporary,
        tag = "Skills"
    ),
    BlogSource(
        title = "Know Your Workplace Rights",
        description = "A concise guide to the rights and reasonable accommodations you can ask for as an employee.",
        imageRes = R.drawable.ic_temporary,
        tag = "Rights"
    )
)
