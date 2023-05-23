package one.two.controller

import jakarta.validation.Valid
import one.two.controller.mapping.toTask
import one.two.controller.mapping.toTaskDto
import one.two.controller.mapping.toUpdateTaskRequest
import one.two.model.dto.CreateTaskRequestDto
import one.two.model.dto.TaskDto
import one.two.model.dto.UpdateTaskRequestDto
import one.two.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class TaskController(private val taskService: TaskService) {

    @GetMapping("all-tasks")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> =
        ResponseEntity(taskService.getAllTasks().map { it.toTaskDto() }, HttpStatus.OK)

    @GetMapping("open-tasks")
    fun getAllOpenTasks(): ResponseEntity<List<TaskDto>> =
        ResponseEntity(taskService.getAllOpenTasks().map { it.toTaskDto() }, HttpStatus.OK)

    @GetMapping("closed-tasks")
    fun getAllClosedTasks(): ResponseEntity<List<TaskDto>> =
        ResponseEntity(taskService.getAllClosedTasks().map { it.toTaskDto() }, HttpStatus.OK)

    @GetMapping("task/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskDto> =
        ResponseEntity(taskService.getTaskById(id).toTaskDto(), HttpStatus.OK)

    @PostMapping("/create")
    fun createTask(
        @Valid @RequestBody createRequest: CreateTaskRequestDto
    ): ResponseEntity<TaskDto> {
        val task = taskService.createTask(createRequest.toTask()).toTaskDto()
        return ResponseEntity(task, HttpStatus.OK)
    }

    @PatchMapping("update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: UpdateTaskRequestDto
    ): ResponseEntity<TaskDto> {
        val task = taskService.updateTask(updateRequest.toUpdateTaskRequest(id)).toTaskDto()
        return ResponseEntity(task, HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(taskService.deleteTask(id), HttpStatus.OK)
}