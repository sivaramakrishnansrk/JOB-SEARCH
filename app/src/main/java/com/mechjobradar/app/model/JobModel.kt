package com.mechjobradar.app.model

enum class CompanyTier(val label: String) {
    OEM("Automotive OEM"),
    TIER_1("Tier-1 Global Supplier"),
    INDIAN_TIER_1("Tier-1 Indian Conglomerate"),
    HEAVY_ENG("Heavy & Commercial"),
    EV_MFG("EV Manufacturing")
}

data class MechJobPost(
    val id: String,
    val title: String,
    val company: String,
    val tier: CompanyTier,
    val domain: String,
    val plantLocation: String, // e.g. Oragadam, Sriperumbudur, Hosur, Coimbatore, Thoothukudi
    val minExpYears: Int,
    val maxExpYears: Int,
    val description: String,
    val keySkills: List<String>,
    val applyUrl: String,
    val postedHoursAgo: Int,
    val sourcePlatform: String
)

data class NotificationItem(
    val id: String,
    val jobTitle: String,
    val company: String,
    val location: String,
    val applyUrl: String,
    val receivedAtTimestamp: Long = System.currentTimeMillis(),
    val isEarlyAlert: Boolean = true
)
