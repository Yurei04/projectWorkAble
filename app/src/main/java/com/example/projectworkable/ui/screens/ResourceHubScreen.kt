package com.example.projectworkable.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
    val context = LocalContext.current

    // State for dialog
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<HubItem?>(null) }

    val resources = remember {
        listOf(
            // Employment Resources
            HubItem.Resource(
                title = "Job Accommodation Network (JAN)",
                description = "Free consulting service about workplace accommodations and disability employment issues.",
                imageResId = R.drawable.ic_temporary,
                tag = "Employment",
                linkUrl = "https://askjan.org"
            ),
            HubItem.Resource(
                title = "Disability Rights & Employment Guide",
                description = "Know your rights under ADA and learn about reasonable accommodations in the workplace.",
                imageResId = R.drawable.ic_temporary,
                tag = "Legal Rights",
                linkUrl = "https://www.eeoc.gov/disability-discrimination"
            ),
            HubItem.Resource(
                title = "Resume Writing for PWD",
                description = "Tips and templates for creating accessible, professional resumes that highlight your skills.",
                imageResId = R.drawable.ic_temporary,
                tag = "Career Development",
                linkUrl = "https://www.disability.gov/resource/disability-govs-guide-to-employment"
            ),

            // Accessibility Resources
            HubItem.Resource(
                title = "Web Accessibility Guidelines (WCAG)",
                description = "International standards for making web content accessible to people with disabilities.",
                imageResId = R.drawable.ic_temporary,
                tag = "Web Accessibility",
                linkUrl = "https://www.w3.org/WAI/WCAG21/quickref/"
            ),
            HubItem.Resource(
                title = "Microsoft Accessibility Guides",
                description = "Comprehensive guides for using accessibility features across Microsoft products.",
                imageResId = R.drawable.ic_temporary,
                tag = "Technology",
                linkUrl = "https://www.microsoft.com/en-us/accessibility"
            ),
            HubItem.Resource(
                title = "Apple Accessibility Features",
                description = "Learn about VoiceOver, Switch Control, and other built-in accessibility features.",
                imageResId = R.drawable.ic_temporary,
                tag = "Technology",
                linkUrl = "https://www.apple.com/accessibility/"
            ),

            // Education & Training
            HubItem.Resource(
                title = "Free Online Courses - Coursera",
                description = "Accessible online learning with courses in technology, business, and career skills.",
                imageResId = R.drawable.ic_temporary,
                tag = "Education",
                linkUrl = "https://www.coursera.org/accessibility"
            ),
            HubItem.Resource(
                title = "LinkedIn Learning Accessibility",
                description = "Professional development courses with closed captions and accessible learning materials.",
                imageResId = R.drawable.ic_temporary,
                tag = "Skill Development",
                linkUrl = "https://www.linkedin.com/learning/"
            ),

            // Financial Assistance
            HubItem.Resource(
                title = "Social Security Disability Benefits",
                description = "Information about SSDI and SSI benefits, eligibility, and application process.",
                imageResId = R.drawable.ic_temporary,
                tag = "Financial Aid",
                linkUrl = "https://www.ssa.gov/disability/"
            ),
            HubItem.Resource(
                title = "Assistive Technology Funding",
                description = "Guide to funding sources for assistive technology devices and services.",
                imageResId = R.drawable.ic_temporary,
                tag = "Financial Aid",
                linkUrl = "https://www.ataporg.org/financial-loan-programs"
            ),

            // Community & Support
            HubItem.Resource(
                title = "Disability Rights Education Network",
                description = "Connect with advocacy groups and learn about disability rights and legislation.",
                imageResId = R.drawable.ic_temporary,
                tag = "Advocacy",
                linkUrl = "https://dredf.org"
            ),
            HubItem.Resource(
                title = "National Disability Institute",
                description = "Financial wellness programs and resources for people with disabilities.",
                imageResId = R.drawable.ic_temporary,
                tag = "Financial Wellness",
                linkUrl = "https://www.nationaldisabilityinstitute.org"
            )
        )
    }

    val tools = remember {
        listOf(
            // Screen Readers & Navigation
            HubItem.Tool(
                title = "TalkBack",
                description = "Google's built-in screen reader for Android devices with gesture navigation.",
                iconResId = R.drawable.ic_temporary,
                category = "Screen Reader",
                packageName = "com.google.android.marvin.talkback"
            ),
            HubItem.Tool(
                title = "Voice Access",
                description = "Control your Android device completely with spoken commands, hands-free.",
                iconResId = R.drawable.ic_temporary,
                category = "Voice Control",
                packageName = "com.google.android.apps.accessibility.voiceaccess"
            ),
            HubItem.Tool(
                title = "Switch Access",
                description = "Navigate your device using adaptive switches instead of the touchscreen.",
                iconResId = R.drawable.ic_temporary,
                category = "Switch Control",
                packageName = "com.google.android.accessibility.switchaccess"
            ),

            // Communication Apps
            HubItem.Tool(
                title = "Live Transcribe",
                description = "Real-time speech-to-text transcription for conversations and meetings.",
                iconResId = R.drawable.ic_temporary,
                category = "Communication",
                packageName = "com.google.audio.hearing.visualization.accessibility.scribe"
            ),
            HubItem.Tool(
                title = "Sound Amplifier",
                description = "Boost and filter sounds around you for better hearing clarity.",
                iconResId = R.drawable.ic_temporary,
                category = "Hearing Aid",
                packageName = "com.google.android.accessibility.soundamplifier"
            ),
            HubItem.Tool(
                title = "Ava - Captions & Subtitles",
                description = "24/7 live captions for conversations, meetings, and phone calls.",
                iconResId = R.drawable.ic_temporary,
                category = "Communication",
                packageName = "com.transcense.ava"
            ),

            // Reading & Text
            HubItem.Tool(
                title = "Voice Dream Reader",
                description = "Premium text-to-speech app that reads documents, web pages, and ebooks aloud.",
                iconResId = R.drawable.ic_temporary,
                category = "Text-to-Speech",
                packageName = "voicedream.reader"
            ),
            HubItem.Tool(
                title = "Be My Eyes",
                description = "Connect with volunteers for visual assistance through live video calls.",
                iconResId = R.drawable.ic_temporary,
                category = "Visual Assistance",
                packageName = "com.bemyeyes.bemyeyes"
            ),
            HubItem.Tool(
                title = "Seeing AI",
                description = "Microsoft's AI-powered app that narrates the world for blind and low vision users.",
                iconResId = R.drawable.ic_temporary,
                category = "Visual Assistance",
                packageName = "com.microsoft.seeingai"
            ),

            // Productivity & Organization
            HubItem.Tool(
                title = "Dragon Anywhere",
                description = "Professional-grade voice dictation for creating documents and emails.",
                iconResId = R.drawable.ic_temporary,
                category = "Dictation",
                packageName = "com.nuance.dragon.anywhere"
            ),
            HubItem.Tool(
                title = "Microsoft Lens",
                description = "Scan documents, whiteboards, and make them accessible with text recognition.",
                iconResId = R.drawable.ic_temporary,
                category = "OCR Scanner",
                packageName = "com.microsoft.office.officelens"
            ),
            HubItem.Tool(
                title = "Evernote",
                description = "Accessible note-taking with voice recording, text formatting, and organization.",
                iconResId = R.drawable.ic_temporary,
                category = "Note-Taking",
                packageName = "com.evernote"
            ),

            // Daily Living
            HubItem.Tool(
                title = "Magnifier - Magnifying Glass",
                description = "Turn your phone into a powerful magnifying glass with flashlight.",
                iconResId = R.drawable.ic_temporary,
                category = "Vision Aid",
                packageName = "com.app2u.magnifier"
            ),
            HubItem.Tool(
                title = "Big Launcher",
                description = "Simple, large-icon home screen for seniors and those with vision impairments.",
                iconResId = R.drawable.ic_temporary,
                category = "Accessibility Launcher",
                packageName = "name.kunes.android.launcher.activity"
            ),
            HubItem.Tool(
                title = "Lookout by Google",
                description = "AI assistant that helps identify objects, text, and people around you.",
                iconResId = R.drawable.ic_temporary,
                category = "Visual Assistance",
                packageName = "com.google.android.apps.accessibility.reveal"
            ),

            // Job Search & Networking
            HubItem.Tool(
                title = "LinkedIn",
                description = "Professional networking platform to connect with employers and build your career.",
                iconResId = R.drawable.ic_temporary,
                category = "Job Search",
                packageName = "com.linkedin.android"
            ),
            HubItem.Tool(
                title = "Indeed Job Search",
                description = "Find accessible job listings with filters for remote work and accommodations.",
                iconResId = R.drawable.ic_temporary,
                category = "Job Search",
                packageName = "com.indeed.android.jobsearch"
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(list) { item ->
                when (item) {
                    is HubItem.Resource ->
                        ResourceHubCard(item) {
                            selectedItem = item
                            showDialog = true
                        }

                    is HubItem.Tool ->
                        ToolHubCard(item) {
                            selectedItem = item
                            showDialog = true
                        }
                }
            }
        }
    }

    // Dialog for showing details and actions
    if (showDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = when (selectedItem) {
                        is HubItem.Resource -> (selectedItem as HubItem.Resource).title
                        is HubItem.Tool -> (selectedItem as HubItem.Tool).title
                        else -> ""
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = when (selectedItem) {
                            is HubItem.Resource -> (selectedItem as HubItem.Resource).description
                            is HubItem.Tool -> (selectedItem as HubItem.Tool).description
                            else -> ""
                        }
                    )

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    when (val item = selectedItem) {
                        is HubItem.Resource -> {
                            Text(
                                text = "Category: ${item.tag}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        is HubItem.Tool -> {
                            Text(
                                text = "Category: ${item.category}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        null -> TODO()
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        when (val item = selectedItem) {
                            is HubItem.Resource -> {
                                // Open link in browser
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.linkUrl))
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    // Handle error - could show a toast
                                }
                            }
                            is HubItem.Tool -> {
                                // Try to launch app, fallback to Play Store
                                try {
                                    val launchIntent = context.packageManager.getLaunchIntentForPackage(item.packageName)
                                    if (launchIntent != null) {
                                        context.startActivity(launchIntent)
                                    } else {
                                        // App not installed, open Play Store
                                        val playStoreIntent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("market://details?id=${item.packageName}")
                                        )
                                        context.startActivity(playStoreIntent)
                                    }
                                } catch (e: Exception) {
                                    // Fallback to web Play Store
                                    try {
                                        val webIntent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=${item.packageName}")
                                        )
                                        context.startActivity(webIntent)
                                    } catch (e2: Exception) {
                                        // Handle error
                                    }
                                }
                            }

                            null -> TODO()
                        }
                        showDialog = false
                    }
                ) {
                    Text(
                        when (selectedItem) {
                            is HubItem.Resource -> "Open Link"
                            is HubItem.Tool -> "Open App"
                            else -> "Open"
                        }
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}