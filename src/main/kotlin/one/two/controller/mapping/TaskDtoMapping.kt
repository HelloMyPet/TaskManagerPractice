package one.two.controller.mapping

import one.two.model.*
import one.two.model.dto.CreateTaskRequestDto
import one.two.model.dto.PriorityDto
import one.two.model.dto.TaskDto
import one.two.model.dto.UpdateTaskRequestDto
import java.lang.IllegalArgumentException

fun CreateTaskRequestDto.toTask(): Task {

    return Task(
        description = description,
        isReminderSet = isReminder,
        isTaskOpen = isTaskOpen,
        priority = priority.toPriority()
    )
}

fun UpdateTaskRequestDto.toUpdateTaskRequest(id: Long): UpdateTaskRequest {
    return UpdateTaskRequest(
        id = id,
        description = description,
        isReminderSet = isReminderSet,
        isTaskOpen = isTaskOpen,
        priority = priority?.toPriority()
    )
    /*return Task(
        id = id,
        description = description ?: "",
        isReminderSet = isReminderSet ?: false,
        isTaskOpen = isTaskOpen ?: false,
        priority = priority?.toPriority() ?: Priority.MINOR
    )*/
}


fun PriorityDto.toPriority(): Priority {
    return when (this) {
        PriorityDto.MINOR -> Priority.MINOR
        PriorityDto.NORMAL -> Priority.NORMAL
        PriorityDto.CRITICAL -> Priority.CRITICAL

    }
}

fun Task.toTaskDto(): TaskDto {
    return TaskDto(
        id = id ?: throw IllegalArgumentException("createOn must be present, by Ksenia"),
        description = description,
        isReminderSet = isReminderSet,
        isTaskOpen = isTaskOpen,
        createdOn = createdOn ?: throw IllegalArgumentException("createOn must be present, by Ksenia"),
        priority = priority.toPriorityDto()

    )
}

fun Priority.toPriorityDto(): PriorityDto {
    return when (this) {
        Priority.MINOR -> PriorityDto.MINOR
        Priority.NORMAL -> PriorityDto.NORMAL
        Priority.CRITICAL -> PriorityDto.CRITICAL

    }
}
