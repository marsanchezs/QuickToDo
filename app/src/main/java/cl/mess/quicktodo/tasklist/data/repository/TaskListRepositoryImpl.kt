package cl.mess.quicktodo.tasklist.data.repository

import cl.mess.quicktodo.tasklist.data.mapper.toTasks
import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import cl.mess.quicktodo.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class TaskListRepositoryImpl @Inject constructor(
    private val remote: TaskListRemote
) : TaskListRepository {
    override suspend fun getTasks() = remote.getTasks().toTasks()
}
