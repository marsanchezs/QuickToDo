package cl.mess.quicktodo.tasklist.domain.usecases

import cl.mess.quicktodo.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskListRepository
) {
    suspend fun getTasks() = repository.getTasks()
}
