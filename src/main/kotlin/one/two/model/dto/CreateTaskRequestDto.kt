package one.two.model.dto

import jakarta.validation.constraints.NotBlank

//Request class for creating a new task
data class CreateTaskRequestDto(
    @NotBlank(message = "Description can't be empty")
    val description: String,

    val isReminder: Boolean,

    val isTaskOpen: Boolean,

    val priority: PriorityDto

)
