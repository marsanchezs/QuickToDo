package cl.mess.quicktodo.tasklist.data

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
class TaskListRepositoryTest {

    private val remote: TaskListRemote = mockk()
    private lateinit var repository: TaskListRepository

    @Before
    fun setUp() {
        repository = TaskListRepository(remote)
    }

    @Test
    fun `given remote returns tasks when getTasks is called then it returns the expected list`() = runTest {
        // Given
        val expectedTasks = makeRemoteTasks()
        stubRemoteGetTasks(expectedTasks)

        // When
        val result = repository.getTasks()

        // Then
        assertEquals(expectedTasks, result)
        coVerify(exactly = 1) { remote.getTasks() }
    }

    private fun stubRemoteGetTasks(expected: List<RemoteTask>) {
        coEvery { remote.getTasks() } returns expected
    }
}
