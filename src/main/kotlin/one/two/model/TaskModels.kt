package one.two.model

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val description: String,
    val isReminderSet: Boolean,
    val isTaskOpen: Boolean,
    val createdOn: LocalDateTime? = null,
    val priority: Priority
)

enum class Priority {
    MINOR,NORMAL,CRITICAL
}

data class UpdateTaskRequest(
    val id: Long,
    val description: String? = null,
    val isReminderSet: Boolean? = null,
    val isTaskOpen: Boolean? = null,
    val priority: Priority? = null
)
