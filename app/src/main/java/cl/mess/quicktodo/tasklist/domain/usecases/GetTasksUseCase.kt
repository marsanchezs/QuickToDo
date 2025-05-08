package cl.mess.quicktodo.tasklist.domain.usecases

import cl.mess.quicktodo.tasklist.data.TaskListRepository
import cl.mess.quicktodo.tasklist.domain.mapper.TaskListMapper
import cl.mess.quicktodo.tasklist.domain.model.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskListRepository,
    private val mapper: TaskListMapper
) {
    suspend fun getTasks(): List<Task> {
        val remote = repository.getTasks()
        val tasks = with(receiver = mapper) { remote.toTasks() }
        return tasks
    }
}
