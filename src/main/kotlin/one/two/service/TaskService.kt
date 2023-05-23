package one.two.service

import one.two.controller.mapping.toPriorityDto
import one.two.exception.BadRequestException
import one.two.exception.TaskNotFoundException
import one.two.model.Task
import one.two.model.UpdateTaskRequest
import one.two.model.dao.TaskEntity
import one.two.repository.TaskRepository
import one.two.repository.mapping.toPriorityEntity
import one.two.repository.mapping.toTask
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class TaskService(
    private var taskDataService: TaskDataService
) {

    fun createTask(task: Task): Task {
        if (taskDataService.doesDescriptionExist(task)) {
            throw BadRequestException("There is already a task with description: ${task.description}")
        }
        return taskDataService.save(task)
    }

    fun getAllTasks(): List<Task> {
        return taskDataService.getAllTasks()
    }

    fun getAllOpenTasks(): List<Task> {
        return taskDataService.getAllOpenTasks()
    }

    fun getAllClosedTasks(): List<Task> {
        return taskDataService.getAllClosedTasks()
    }

    fun getTaskById(id: Long): Task {
        return taskDataService.getTaskById(id)
    }

    fun updateTask(updateTaskRequest: UpdateTaskRequest): Task {
        val taskId = updateTaskRequest.id
        getTaskById(taskId)
        val existingTask: Task = getTaskById(taskId)
        val taskForUpdate = createTaskForUpdate(existingTask, updateTaskRequest)
        return taskDataService.save(taskForUpdate.toTask())
    }

    private fun createTaskForUpdate(existingTask: Task, updateTaskRequest: UpdateTaskRequest): TaskEntity {
        return TaskEntity(
            id = updateTaskRequest.id,
            description = updateTaskRequest.description ?: existingTask.description,
            isReminderSet = updateTaskRequest.isReminderSet ?: existingTask.isReminderSet,
            isTaskOpen = updateTaskRequest.isTaskOpen ?: existingTask.isTaskOpen,
            priority = updateTaskRequest.priority?.toPriorityEntity() ?: existingTask.priority.toPriorityEntity(),
        )
    }

    fun deleteTask(id: Long): String {
        taskDataService.deleteTask(id)
        return "Task with id: $id has been deleted."
    }


}

