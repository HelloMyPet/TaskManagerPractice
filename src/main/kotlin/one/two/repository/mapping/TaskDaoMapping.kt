package one.two.repository.mapping

import one.two.model.dao.PriorityEntity
import one.two.model.Priority
import one.two.model.Task
import one.two.model.dao.TaskEntity

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        description = description,
        isReminderSet = isReminderSet,
        isTaskOpen = isTaskOpen,
        priority = priority.toPriorityEntity()
    )
}


fun Priority.toPriorityEntity(): PriorityEntity {
    return when (this) {
        Priority.MINOR -> PriorityEntity.MINOR
        Priority.NORMAL -> PriorityEntity.NORMAL
        Priority.CRITICAL -> PriorityEntity.CRITICAL

    }
}

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        description = description,
        isReminderSet = isReminderSet,
        isTaskOpen = isTaskOpen,
        createdOn = createdOn,
        priority = priority.toPriority()
    )
}

fun PriorityEntity.toPriority(): Priority {
    return when (this) {
        PriorityEntity.MINOR -> Priority.MINOR
        PriorityEntity.NORMAL -> Priority.NORMAL
        PriorityEntity.CRITICAL -> Priority.CRITICAL

    }
}

