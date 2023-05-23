package one.two.config

import one.two.repository.TaskRepository
import one.two.service.TaskDaoService
import one.two.service.TaskDataService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataServiceConfig {
    @Bean
    fun taskDataService(repository: TaskRepository): TaskDataService {
        return TaskDaoService(repository)
    }

}