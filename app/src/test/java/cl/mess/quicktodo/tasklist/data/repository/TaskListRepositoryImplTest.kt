package cl.mess.quicktodo.tasklist.data.repository

import cl.mess.quicktodo.tasklist.data.mapper.toTasks
import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.data.source.TaskListRemote
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeRemoteTasks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class TaskListRepositoryImplTest {

    private val remote: TaskListRemote = mockk()
    private lateinit var repository: TaskListRepositoryImpl

    @Before
    fun setUp() {
        repository = TaskListRepositoryImpl(remote)
    }

    @Test
    fun `given remote returns tasks when getTasks is called then it returns the mapped list`() = runTest {
        // Given
        val remoteTasks = makeRemoteTasks()
        stubRemoteGetTasks(remoteTasks)

        // When
        val result = repository.getTasks()

        // Then
        assertEquals(remoteTasks.toTasks(), result)
        coVerify(exactly = 1) { remote.getTasks() }
    }

    private fun stubRemoteGetTasks(tasks: List<RemoteTask>) {
        coEvery { remote.getTasks() } returns tasks
    }
}
