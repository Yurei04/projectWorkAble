package com.example.projectworkable.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectworkable.R
import com.example.projectworkable.ui.components.JobCard // Assuming JobCard is a versatile component for both

// --- Data Models for Tabs ---

data class Resource(
    val title: String,
    val description: String,
    val imageResId: Int, // Changed to hold the resource ID
    val tag: String,
    val linkUrl: String
)

data class Tool(
    val title: String,
    val description: String,
    val iconResId: Int, // Changed to hold the resource ID
    val category: String,

    val packageName: String
)

// --- Composable for the Tab Switch Button ---

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
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
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

// --- Main Screen Composable ---

enum class ContentTab {
    RESOURCES,
    TOOLS
}

@Composable
fun ResourceHubScreen() {
    // --- Fake Data (using resource IDs) ---
    val resources = remember {
        listOf(
            Resource(
                title = "Accessibility Web Design Guide",
                description = "Essential guidelines for creating WCAG compliant websites. A must-read for developers.",
                imageResId = R.drawable.ic_temporary,
                tag = "Web Design",
                linkUrl = "https://example.com/web-accessibility"
            ),
            Resource(
                title = "Job Search Tips for Professionals",
                description = "Proven strategies to optimize your resume and ace interviews.",
                imageResId = R.drawable.ic_temporary,
                tag = "Career Advice",
                linkUrl = "https://example.com/job-tips"
            ),
            Resource(
                title = "Understanding Screen Readers",
                description = "A comprehensive overview of how screen readers work and how to test for them.",
                imageResId = R.drawable.ic_temporary,
                tag = "Technology",
                linkUrl = "https://example.com/screen-readers"
            )
        )
    }

    val tools = remember {
        listOf(
            Tool(
                title = "Voice Typing Assistant",
                description = "An app to convert spoken words into text with high accuracy.",
                iconResId = R.drawable.ic_temporary,
                category = "Input Aid",
                packageName = "com.example.voicetyping"
            ),
            Tool(
                title = "Color Contrast Checker",
                description = "Verify color combinations for accessibility compliance (WCAG 2.1).",
                iconResId = R.drawable.ic_temporary,
                category = "Design Tool",
                packageName = "com.example.contrastchecker"
            ),
            Tool(
                title = "Focus Planner",
                description = "A minimal tool designed to help manage time and reduce distractions.",
                iconResId = R.drawable.ic_temporary,
                category = "Productivity",
                packageName = "com.example.focusplanner"
            )
        )
    }

    // --- State and Animation ---

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    var selectedTab by remember { mutableStateOf(ContentTab.RESOURCES) }

    // Determine the list to display based on the selected tab
    val contentList: List<Any> = when (selectedTab) {
        ContentTab.RESOURCES -> resources
        ContentTab.TOOLS -> tools
    }

    // --- UI Layout ---

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(tween(700)) + fadeIn(tween(700))
        ) {
            Text(
                text = if (selectedTab == ContentTab.RESOURCES) "Resources" else "Tools",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Empowering individuals with disabilities to find meaningful opportunities.",
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 12.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Tab Switcher Row ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TabButton(
                text = "Resources",
                isSelected = selectedTab == ContentTab.RESOURCES,
                onClick = { selectedTab = ContentTab.RESOURCES }
            )
            Spacer(modifier = Modifier.width(16.dp))
            TabButton(
                text = "Tools",
                isSelected = selectedTab == ContentTab.TOOLS,
                onClick = { selectedTab = ContentTab.TOOLS }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Content List ---
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp), // Add padding to the bottom of the list
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contentList) { item ->
                when (item) {
                    is Resource -> {
                        JobCard( // Reusing JobCard component for resources
                            title = item.title,
                            description = item.description,
                            image = painterResource(id = item.imageResId), // Correct usage
                            onClick = { /* Handle opening link: item.linkUrl */ },
                            tag = item.tag
                        )
                    }
                    is Tool -> {
                        JobCard( // Reusing JobCard component for tools
                            title = item.title,
                            description = item.description,
                            image = painterResource(id = item.iconResId), // Correct usage
                            onClick = { /* Handle launching app: item.packageName */ },
                            tag = item.category
                        )
                    }
                }
            }
        }
    }
}
