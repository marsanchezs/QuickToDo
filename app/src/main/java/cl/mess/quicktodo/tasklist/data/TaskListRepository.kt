package cl.mess.quicktodo.tasklist.data

import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import javax.inject.Inject

class TaskListRepository @Inject constructor(
    private val remote: TaskListRemote
) {
    suspend fun getTasks() = remote.getTasks()
}
