package cl.mess.quicktodo.tasklist.domain.usecases

import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.tasklist.domain.repository.TaskListRepository
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
    private lateinit var useCase: GetTasksUseCase

    @Before
    fun setUp() {
        useCase = GetTasksUseCase(repository)
    }

    @Test
    fun `given repository returns tasks when getTasks is called then it returns the expected list`() = runTest {
        // Given
        val expectedTasks = makeTasks()
        stubRepositoryGetTasks(expectedTasks)

        // When
        val result = useCase.getTasks()

        // Then
        assertEquals(expectedTasks, result)
        coVerify(exactly = 1) { repository.getTasks() }
    }

    private fun stubRepositoryGetTasks(tasks: List<Task>) {
        coEvery { repository.getTasks() } returns tasks
    }
}
