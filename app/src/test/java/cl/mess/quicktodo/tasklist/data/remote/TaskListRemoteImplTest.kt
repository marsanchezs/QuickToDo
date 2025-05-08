package cl.mess.quicktodo.tasklist.data.remote

import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.data.remote.retrofit.TaskListWebService
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
class TaskListRemoteImplTest {

    private val api: TaskListWebService = mockk()
    private lateinit var remote: TaskListRemoteImpl

    @Before
    fun setUp() {
        remote = TaskListRemoteImpl(api)
    }

    @Test
    fun `given api returns tasks when getTasks is called then it returns the expected list`() = runTest {
        // Given
        val expectedTasks = makeRemoteTasks()
        stubGetTasks(expectedTasks)

        // When
        val result = remote.getTasks()

        // Then
        assertEquals(expectedTasks, result)
        coVerify(exactly = 1) { api.getTasks() }
    }

    private fun stubGetTasks(expected: List<RemoteTask>) {
        coEvery { api.getTasks() } returns expected
    }
}
