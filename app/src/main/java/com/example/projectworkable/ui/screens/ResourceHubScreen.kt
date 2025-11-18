package com.example.projectworkable.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectworkable.model.HubItem
import com.example.projectworkable.R
import com.example.projectworkable.ui.components.ResourceHubCard
import com.example.projectworkable.ui.components.ToolHubCard
import com.example.projectworkable.ui.screens.ContentTab.*
import com.example.projectworkable.ui.components.TabButton

enum class ContentTab { RESOURCES, TOOLS }

@Composable
fun ResourceHubScreen() {

    val resources = remember {
        listOf(
            HubItem.Resource(
                title = "Accessibility Web Design Guide",
                description = "Create WCAG compliant interfaces.",
                imageResId = R.drawable.ic_temporary,
                tag = "Web Design",
                linkUrl = "https://example.com"
            )
        )
    }

    val tools = remember {
        listOf(
            HubItem.Tool(
                title = "Voice Typing Assistant",
                description = "Convert speech to text.",
                iconResId = R.drawable.ic_temporary,
                category = "Input Aid",
                packageName = "com.example.voicetyping"
            )
        )
    }

    var tab by remember { mutableStateOf(RESOURCES) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val list = if (tab == RESOURCES) resources else tools

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(tween(700)) + fadeIn(tween(700))
        ) {
            Text(
                text = if (tab == RESOURCES) "Resources" else "Tools",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            TabButton("Resources", tab == RESOURCES) { tab = RESOURCES }
            Spacer(Modifier.width(12.dp))
            TabButton("Tools", tab == TOOLS) { tab = TOOLS }
        }

        Spacer(Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list) { item ->
                when (item) {
                    is HubItem.Resource ->
                        ResourceHubCard(item) { /* open link */ }

                    is HubItem.Tool ->
                        ToolHubCard(item) { /* launch package */ }
                }
            }
        }
    }
}
