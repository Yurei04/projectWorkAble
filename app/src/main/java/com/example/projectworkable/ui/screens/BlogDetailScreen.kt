package com.example.projectworkable.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
fun BlogDetailScreen(
    blogId: Int,
    onBackClick: () -> Unit // 💡 New parameter for the exit action
) {

    val post = getDummyPostById(blogId)

    if (post == null) {
        Text("Error: Post not found.", modifier = Modifier.padding(16.dp))
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(post.title, maxLines = 1) })
        },
        // 💡 FloatingActionButton (FAB) to exit the screen
        floatingActionButton = {
            FloatingActionButton(
                onClick = onBackClick, // Call the action passed from NavGraph
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Article",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End // Positions the FAB bottom right
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                // Feature Image (using local R.drawable resource)
                Image(
                    painter = painterResource(id = post.imageRes),
                    contentDescription = post.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    // ... (Tag, Title, and Article Body remain the same) ...
                    Text(text = post.tag.uppercase(), style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = post.title, style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold, lineHeight = 40.sp), color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = post.fullContent, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

// Data class for full article content (using imageRes: Int)
data class FullBlogPost(
    val id: Int,
    val title: String,
    val tag: String,
    val imageRes: Int,
    val fullContent: String
)

// Helper function using R.drawable references
fun getDummyPostById(id: Int): FullBlogPost? {
    return when (id) {
        1 -> FullBlogPost(id = 1, title = "Navigating Discrimination in the Interview Process", tag = "Advocacy", imageRes = R.drawable.ic_temporary, fullContent = "If you encounter unfair questions during a job interview, remember that various laws protect candidates with disabilities. Always document the specifics of the conversation: the question asked, the person who asked it, and the date. You do not have to disclose your disability unless you choose to, and questions about medical history unrelated to the job requirements are illegal. Our guide provides step-by-step actions on how to address discrimination claims professionally...")
        2 -> FullBlogPost(id = 2, title = "What is 'Reasonable Accommodation' and How to Ask For It", tag = "Rights", imageRes = R.drawable.ic_temporary, fullContent = "Reasonable accommodation is any change in the work environment or the way things are usually done that enables an individual with a disability to enjoy equal employment opportunities. This might include adjustable furniture, specialized software, or a modified work schedule. To make a request, submit it in writing, specify the limitation caused by the disability, and propose a specific accommodation solution...")
        3 -> FullBlogPost(id = 3, title = "Salary Gap: How to Ensure You Get Paid Fairly", tag = "Career", imageRes = R.drawable.ic_temporary, fullContent = "Benchmark your worth and negotiate a salary that reflects your skill, not your disability status. Research industry standards and prepare evidence of your qualifications. Always ask for a salary range upfront and be ready to articulate your value based on market data, not your previous salary history.")
        4 -> FullBlogPost(id = 4, title = "Microaggressions at Work: A Guide to Responding", tag = "Stories", imageRes = R.drawable.ic_temporary, fullContent = "Microaggressions are subtle, often unintentional, slights or comments that communicate hostile or negative messages to people based on their marginalized group membership. Responding effectively requires careful judgment—sometimes it's best to address it directly ('I think you meant to say...'), other times, documenting the incident for HR is the better strategy. We discuss scenarios and appropriate responses here.")
        5 -> FullBlogPost(id = 5, title = "Creating an Inclusive WFH Setup for Chronic Illness", tag = "WFH", imageRes = R.drawable.ic_temporary, fullContent = "Working from home offers immense flexibility, but it requires mindful setup for those managing chronic illness. Focus on adaptive equipment like ergonomic chairs, specialized input devices, and ambient controls (lighting, temperature). Establishing a flexible daily routine that honors energy limitations is more important than mirroring a traditional 9-to-5 schedule. Communicate your needs clearly with your manager to maintain support.")
        6 -> FullBlogPost(id = 6, title = "The Power of Disclosure: When and How to Talk to HR", tag = "Advocacy", imageRes = R.drawable.ic_temporary, fullContent = "Deciding whether to disclose your disability is a personal choice. Legally, disclosure is often necessary to formally request accommodation, but it can be done strategically. We provide a timeline: 1) Research your company's policy, 2) Prepare documentation, and 3) Schedule a private, documented conversation with HR, focusing on needs and solutions rather than medical history.")
        else -> null
    }
}