package com.mechjobradar.app.data

import androidx.compose.runtime.mutableStateListOf
import com.mechjobradar.app.model.NotificationItem

object NotificationStore {
    val notificationHistory = mutableStateListOf<NotificationItem>()

    fun addNotification(item: NotificationItem) {
        if (notificationHistory.none { it.id == item.id }) {
            notificationHistory.add(0, item)
        }
    }

    fun clearAll() {
        notificationHistory.clear()
    }
}

