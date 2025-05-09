package cl.mess.quicktodo.tasklist.domain.repository

import cl.mess.quicktodo.tasklist.domain.model.Task

interface TaskListRepository {
    suspend fun getTasks(): List<Task>
}
