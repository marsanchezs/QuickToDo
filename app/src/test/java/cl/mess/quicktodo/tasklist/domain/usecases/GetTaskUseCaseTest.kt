package cl.mess.quicktodo.tasklist.domain.usecases

import cl.mess.quicktodo.tasklist.data.TaskListRepository
import cl.mess.quicktodo.tasklist.data.remote.model.RemoteTask
import cl.mess.quicktodo.tasklist.domain.mapper.TaskListMapper
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeRemoteTasks
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeTasks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetTasksUseCaseTest {

    private val repository: TaskListRepository = mockk()
    private val mapper: TaskListMapper = mockk()
    private lateinit var useCase: GetTasksUseCase

    @Before
    fun setUp() {
        useCase = GetTasksUseCase(repository, mapper)
    }

    @Test
    fun `given repository returns remote tasks when getTasks is called then it returns mapped tasks`() = runTest {
        // Given
        val remoteTasks = makeRemoteTasks()
        val expectedTasks = makeTasks()

        stubRepositoryGetTasks(remoteTasks)
        stubMapperToTasks(remoteTasks, expectedTasks)

        // When
        val result = useCase.getTasks()

        // Then
        assertEquals(expectedTasks, result)
        coVerify(exactly = 1) { repository.getTasks() }
    }

    private fun stubRepositoryGetTasks(remote: List<RemoteTask>) {
        coEvery { repository.getTasks() } returns remote
    }

    private fun stubMapperToTasks(remote: List<RemoteTask>, mapped: List<Task>) {
        coEvery { with(mapper) { remote.toTasks() } } returns mapped
    }
}
