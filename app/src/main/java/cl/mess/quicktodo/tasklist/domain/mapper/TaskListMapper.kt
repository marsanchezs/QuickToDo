package cl.mess.quicktodo.tasklist.domain.mapper

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.domain.model.Task
import javax.inject.Inject

class TaskListMapper @Inject constructor() {
    fun List<RemoteTask>.toTasks() = map { remoteTask -> remoteTask.toTask() }

    private fun RemoteTask.toTask() = Task(
        userId = userId ?: 0,
        id = id ?: 0,
        title = title.orEmpty(),
        completed = completed ?: false
    )
}
