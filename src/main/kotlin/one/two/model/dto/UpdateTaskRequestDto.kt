package one.two.model.dto

data class UpdateTaskRequestDto(
    val description: String?,
    val isReminderSet: Boolean?,
    val isTaskOpen: Boolean?,
    val priority: PriorityDto?
)
