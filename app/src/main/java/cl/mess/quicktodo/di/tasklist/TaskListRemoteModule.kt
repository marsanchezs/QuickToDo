package cl.mess.quicktodo.di.tasklist

import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import cl.mess.quicktodo.tasklist.data.source.TaskListRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskListRemoteModule {
    @Binds
    abstract fun bindTaskListRemote(remoteImpl: TaskListRemoteImpl): TaskListRemote
}
