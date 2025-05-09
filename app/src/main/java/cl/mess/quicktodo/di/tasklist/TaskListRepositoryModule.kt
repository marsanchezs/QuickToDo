package cl.mess.quicktodo.di.tasklist

import cl.mess.quicktodo.tasklist.data.repository.TaskListRepositoryImpl
import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import cl.mess.quicktodo.tasklist.domain.repository.TaskListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskListRepositoryModule {

    @Provides
    @Singleton
    fun provideTaskListRepository(remote: TaskListRemote): TaskListRepository {
        return TaskListRepositoryImpl(remote)
    }
}
