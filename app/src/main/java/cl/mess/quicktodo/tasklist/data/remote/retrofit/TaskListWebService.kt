package cl.mess.quicktodo.tasklist.data.remote.retrofit

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import retrofit2.http.GET

interface TaskListWebService {
    @GET("todos")
    suspend fun getTasks(): List<RemoteTask>
}
