package one.two.service

import one.two.exception.TaskNotFoundException
import one.two.model.Task
import one.two.model.UpdateTaskRequest
import one.two.repository.TaskRepository
import one.two.repository.mapping.toTask
import one.two.repository.mapping.toTaskEntity
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TaskDaoService(private val repository: TaskRepository) : TaskDataService {

    override fun doesDescriptionExist(task: Task): Boolean {
        return repository.doesDescriptionExist(task.description)

    }

    override fun save(task: Task): Task {
        return repository.save(task.toTaskEntity()).toTask()
    }

    override fun checkForTaskId(id: Long) {
        if (!repository.existsById(id)) {
            throw TaskNotFoundException("Task with ID: $id does not exist!")
        }
    }

    override fun getAllTasks(): List<Task> =
        repository.findAll().stream().map { it.toTask() }.collect(Collectors.toList())

    override fun getAllOpenTasks(): List<Task> =
        repository.queryAllOpenTasks().stream().map { it.toTask() }.collect(Collectors.toList())

    override fun getAllClosedTasks(): List<Task> =
        repository.queryAllClosedTasks().stream().map { it.toTask() }.collect(Collectors.toList())

    override fun getTaskById(id: Long): Task {
        checkForTaskId(id)
        return repository.findTaskById(id).toTask()
    }

    override fun updateTask(updateTaskRequest: UpdateTaskRequest): Task {
        TODO("Not yet implemented")
    }

    override fun deleteTask(id: Long): String {
        checkForTaskId(id)
        repository.deleteById(id)
        return "Task with id: $id has been deleted."
    }

}

