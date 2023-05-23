package one.two.service

import one.two.model.Task
import one.two.model.UpdateTaskRequest
import one.two.repository.TaskRepository

interface TaskDataService { //TaskRepository {
    fun doesDescriptionExist(task: Task): Boolean
    fun save(task: Task): Task
    fun getAllTasks(): List<Task>
    fun getAllOpenTasks(): List<Task>
    fun getAllClosedTasks(): List<Task>
    fun getTaskById(id: Long): Task
    fun updateTask(updateTaskRequest: UpdateTaskRequest): Task
    fun deleteTask(id: Long): String
    fun checkForTaskId(id: Long)
    }