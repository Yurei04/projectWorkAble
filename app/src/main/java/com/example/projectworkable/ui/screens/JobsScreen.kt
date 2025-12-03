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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectworkable.R
import com.example.projectworkable.ui.components.JobCard

data class JobSource(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int,
    val tag: String
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JobsScreen(
    jobs: List<JobSource> = sampleJobs(),
    onOpenJob: (Int) -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        /** HEADER UI — same style as BlogScreen */
        item {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(900))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_accessibility),
                        contentDescription = "WorkAble Logo",
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInVertically(tween(700)) + fadeIn(tween(700))
                    ) {
                        Text(
                            text = "WorkAble Jobs",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Opportunities tailored for accessible and inclusive employment.",
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        /** JOB CARDS */
        items(jobs) { job ->
            JobCard(
                title = job.title,
                description = job.description,
                imageRes = job.imageRes,
                tag = job.tag,
                onClick = { onOpenJob(job.id) }
            )
        }
    }
}

fun sampleJobs(): List<JobSource> = listOf(
    JobSource(1, "Software Engineer", "Build modern mobile apps.", R.drawable.ic_temporary, "IT"),
    JobSource(2, "Product Manager", "Lead high-impact product teams.", R.drawable.ic_temporary, "Management"),
    JobSource(3, "Graphic Designer", "Create graphics and brand assets.", R.drawable.ic_temporary, "Design"),
    JobSource(4, "Head Chef", "Lead kitchen operations and menu creation.", R.drawable.ic_temporary, "Culinary")
)
