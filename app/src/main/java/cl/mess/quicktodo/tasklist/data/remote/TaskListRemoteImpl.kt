package cl.mess.quicktodo.tasklist.data.remote

import cl.mess.quicktodo.tasklist.data.remote.retrofit.TaskListWebService
import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import javax.inject.Inject

class TaskListRemoteImpl @Inject constructor(
    private val api: TaskListWebService
) : TaskListRemote {
    override suspend fun getTasks() = api.getTasks()
}
