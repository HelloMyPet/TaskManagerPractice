package one.two.repository

import one.two.model.dao.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface TaskRepository: JpaRepository<TaskEntity, Long> {

    fun findTaskById(id: Long): TaskEntity

    @Query(value = "SELECT * FROM task WHERE is_task_open = TRUE", nativeQuery = true)
    fun queryAllOpenTasks(): List<TaskEntity>

    @Query(value = "SELECT * FROM task WHERE is_task_open = FALSE", nativeQuery = true)
    fun queryAllClosedTasks(): List<TaskEntity>

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM task t WHERE t.description = ?1")
    fun doesDescriptionExist(description: String): Boolean
}