package one.two.service

import one.two.model.Task
import one.two.model.UpdateTaskRequest
import org.springframework.stereotype.Service


@Service
class TaskFileService : TaskDataService {

    override fun doesDescriptionExist(task: Task): Boolean {
        println("doesDescriptionExist is works: return true")
        return true
    }

    override fun save(task: Task): Task {
        println("Save task is works: return $task")
        return task
    }

    override fun getAllTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getAllOpenTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getAllClosedTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(id: Long): Task {
        TODO("Not yet implemented")
    }

    override fun updateTask(updateTaskRequest: UpdateTaskRequest): Task {
        TODO("Not yet implemented")
    }

    override fun deleteTask(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun checkForTaskId(id: Long) {
        TODO("Not yet implemented")
    }


}