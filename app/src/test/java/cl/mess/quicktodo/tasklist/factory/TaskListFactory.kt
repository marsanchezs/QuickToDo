package cl.mess.quicktodo.tasklist.factory

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.utils.RandomFactory

object TaskListFactory {
    fun makeRemoteTasks() = listOf(
        RemoteTask(
            userId = RandomFactory.generateRandomInt(),
            id = RandomFactory.generateRandomInt(),
            title = RandomFactory.generateRandomString(),
            completed = RandomFactory.generateRandomBoolean()
        )
    )

    fun makeTasks() = listOf(
        Task(
            userId = RandomFactory.generateRandomInt(),
            id = RandomFactory.generateRandomInt(),
            title = RandomFactory.generateRandomString(),
            completed = RandomFactory.generateRandomBoolean()
        )
    )

    fun makeTask() = Task(
        userId = RandomFactory.generateRandomInt(),
        id = RandomFactory.generateRandomInt(),
        title = RandomFactory.generateRandomString(),
        completed = RandomFactory.generateRandomBoolean()
    )

    fun makeUpdateTask() = Task(
        userId = 1,
        id = 1,
        title = "Updated Task",
        completed = true
    )
}