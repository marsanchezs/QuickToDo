package cl.mess.quicktodo.tasklist.data.source

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask

interface TaskListRemote {
    suspend fun getTasks(): List<RemoteTask>
}
