package cl.mess.quicktodo.tasklist.factory

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
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
}