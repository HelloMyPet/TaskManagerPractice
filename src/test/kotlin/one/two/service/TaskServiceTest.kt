package one.two.service

import one.two.exception.BadRequestException
import one.two.model.Priority
import one.two.model.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import kotlin.random.Random


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class TaskServiceTest {

    @Autowired
    private lateinit var taskDataService: TaskDataService

    private lateinit var taskService: TaskService


    @BeforeEach
    fun setUp() {
        taskService = TaskService(taskDataService)

        val task = Task(
            description = "my task",
            isReminderSet = true,
            isTaskOpen = true,
            priority = Priority.CRITICAL
        )

        taskService.createTask(task)
        taskService.createTask(task.copy(description = "My task" + Random.nextInt()))

    }


    @Test
    fun `when all tasks get fetched then check if the given size is correct`() {
        val getAllTaskResponse = taskService.getAllTasks()
        assertThat(getAllTaskResponse.size).isEqualTo(2)
    }

    @Test
    fun `when task gets created then check if it gets properly created`() {
        val task = Task(
            description = "my task 123",
            isReminderSet = true,
            isTaskOpen = true,
            priority = Priority.CRITICAL
        )


        val taskResult = taskService.createTask(task)
        assertTask(task, taskResult)
    }


    @Test
    fun `when task gets created with non unique description then check for bad request exception`() {
        val task = Task(
            description = "my task",
            isReminderSet = true,
            isTaskOpen = true,
            priority = Priority.CRITICAL
        )

        val exception = assertThrows<BadRequestException> { taskService.createTask(task) }

        assertThat(exception.message).isEqualTo("There is already a task with description: ${task.description}")
    }

    private fun assertTask(expectedTask: Task, task: Task) {
        assertAll("Verify Task properties",
            { assertEquals(expectedTask.description, task.description) },
            { assertEquals(expectedTask.isReminderSet, task.isReminderSet) },
            { assertEquals(expectedTask.isTaskOpen, task.isTaskOpen) },
            { assertEquals(expectedTask.priority, task.priority) }
        )
    }
}