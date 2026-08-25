package com.mechjobradar.app.model

enum class CompanyTier(val label: String) {
    OEM("Automotive OEM"),
    TIER_1("Tier-1 Supplier"),
    HEAVY_ENG("Heavy Engg & EPC"),
    PRECISION("Precision / Aerospace")
}

data class MechJobPost(
    val id: String,
    val title: String,
    val company: String,
    val tier: CompanyTier,
    val domain: String, // e.g., CAD/Design, QA/QC, Production, Thermal, Automation
    val location: String, // Chennai, Coimbatore, Bengaluru, Hosur, Hyderabad, Sriperumbudur
    val minExpYears: Int,
    val maxExpYears: Int,
    val description: String,
    val keySkills: List<String>,
    val applyUrl: String,
    val postedHoursAgo: Int,
    val sourcePlatform: String // "Official Portal", "LinkedIn", "Direct Portal"
)
