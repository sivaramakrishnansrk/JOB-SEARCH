package com.mechjobradar.app.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mechjobradar.app.data.JobRepository
import com.mechjobradar.app.model.CompanyTier
import com.mechjobradar.app.model.MechJobPost

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MechanicalJobPortalScreen() {
    val context = LocalContext.current
    val allJobs = remember { JobRepository.getProductionMechanicalJobs() }

    var searchQuery by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("All") }
    var selectedDomain by remember { mutableStateOf("All") }
    var selectedTier by remember { mutableStateOf("All") }
    var maxExpFilter by remember { mutableFloatStateOf(8f) }
    var earlyApplyOnly by remember { mutableStateOf(false) }

    val locations = listOf("All", "Chennai", "Coimbatore", "Hosur", "Bengaluru", "Sriperumbudur")
    val domains = listOf("All", "CAD & Design", "QA & Inspection", "R&D / Thermal", "Production & Lean", "Manufacturing & CNC", "Automation / Mechatronics")

    val filteredJobs = allJobs.filter { job ->
        val matchesQuery = searchQuery.isBlank() ||
                job.title.contains(searchQuery, ignoreCase = true) ||
                job.company.contains(searchQuery, ignoreCase = true) ||
                job.keySkills.any { it.contains(searchQuery, ignoreCase = true) }

        val matchesLoc = selectedLocation == "All" || job.location.contains(selectedLocation, ignoreCase = true)
        val matchesDomain = selectedDomain == "All" || job.domain.equals(selectedDomain, ignoreCase = true)
        val matchesTier = selectedTier == "All" || job.tier.name == selectedTier
        val matchesExp = job.minExpYears <= maxExpFilter.toInt()
        val matchesEarly = !earlyApplyOnly || job.postedHoursAgo <= 6

        matchesQuery && matchesLoc && matchesDomain && matchesTier && matchesExp && matchesEarly
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("South India Mechanical Careers", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                        Text("OEM, Tier-1 & Manufacturing Aggregator", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    label = { Text("Search Roles, Skills (CATIA, NDT, CFD, Lean...)") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Location Chips
                Text("Locations (South Region)", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    locations.forEach { loc ->
                        FilterChip(
                            selected = selectedLocation == loc,
                            onClick = { selectedLocation = loc },
                            label = { Text(loc) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Domain Filter Chips
                Text("Engineering Domain", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    domains.forEach { dom ->
                        FilterChip(
                            selected = selectedDomain == dom,
                            onClick = { selectedDomain = dom },
                            label = { Text(dom) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Slider & Early Apply Toggle Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Max Experience: ${maxExpFilter.toInt()} Years", fontWeight = FontWeight.Medium)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = earlyApplyOnly,
                                    onCheckedChange = { earlyApplyOnly = it }
                                )
                                Text("Early Apply (< 6h)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                        Slider(
                            value = maxExpFilter,
                            onValueChange = { maxExpFilter = it },
                            valueRange = 0f..12f,
                            steps = 11
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "Showing ${filteredJobs.size} matching vacancies",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Results List
            items(filteredJobs, key = { it.id }) { job ->
                JobCardItem(
                    job = job,
                    onApplyClicked = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.applyUrl))
                        context.startActivity(intent)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobCardItem(job: MechJobPost, onApplyClicked: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Company + Early Apply Banner
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = job.company,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${job.tier.label} • ${job.sourcePlatform}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                if (job.postedHoursAgo <= 6) {
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "⚡ Early Apply (${job.postedHoursAgo}h ago)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Job Title
            Text(
                text = job.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // Location & Exp
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = "Location", modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(job.location, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Work, contentDescription = "Experience", modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${job.minExpYears}-${job.maxExpYears} Yrs Exp", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(job.description, fontSize = 13.sp, lineHeight = 18.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))

            Spacer(modifier = Modifier.height(10.dp))

            // Key Skills Tag Cloud
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                job.keySkills.forEach { skill ->
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = skill,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Button
            Button(
                onClick = onApplyClicked,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Apply on Official Portal")
            }
        }
    }
}
