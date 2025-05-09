package cl.mess.quicktodo.tasklist.data.mapper

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.domain.model.Task

fun List<RemoteTask>.toTasks() = map { remoteTask -> remoteTask.toTask() }

internal fun RemoteTask.toTask() = Task(
    userId = userId ?: 0,
    id = id ?: 0,
    title = title.orEmpty(),
    completed = completed ?: false
)
