package com.example.projectworkable.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectworkable.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    jobId: Int,
    onBackClick: () -> Unit,
    onApplyClick: (String) -> Unit // New parameter for navigation to application
) {
    val job = getJobById(jobId)

    if (job == null) {
        Text("Error: Job not found.", modifier = Modifier.padding(16.dp))
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(job.title, maxLines = 1) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onBackClick,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            /** IMAGE */
            item {
                Image(
                    painter = painterResource(id = job.imageRes),
                    contentDescription = job.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = job.tag.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = job.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 40.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = job.fullDescription,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            // Apply Now Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Ready to join our team?",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Submit your application today",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { onApplyClick(job.title) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Apply Now",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

/** FULL JOB MODEL */
data class FullJob(
    val id: Int,
    val title: String,
    val tag: String,
    val imageRes: Int,
    val fullDescription: String
)

/** Dummy job database */
fun getJobById(id: Int): FullJob? {
    return when (id) {
        1 -> FullJob(
            id = 1,
            title = "Software Engineer",
            tag = "Engineering",
            imageRes = R.drawable.ic_temporary,
            fullDescription = "As a Software Engineer, you will develop accessible and scalable mobile applications..."
        )
        2 -> FullJob(
            id = 2,
            title = "Product Manager",
            tag = "Product",
            imageRes = R.drawable.ic_temporary,
            fullDescription = "As a Product Manager, you coordinate cross-functional teams..."
        )
        3 -> FullJob(
            id = 3,
            title = "Graphic Designer",
            tag = "Design",
            imageRes = R.drawable.ic_temporary,
            fullDescription = "The Graphic Designer will create brand assets and digital content..."
        )
        4 -> FullJob(
            id = 4,
            title = "Head Chef",
            tag = "Culinary",
            imageRes = R.drawable.ic_temporary,
            fullDescription = "Lead kitchen operations, design menus, and manage food preparation..."
        )
        else -> null
    }
}