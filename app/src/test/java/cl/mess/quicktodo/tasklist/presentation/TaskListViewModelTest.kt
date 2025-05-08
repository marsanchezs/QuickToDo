package cl.mess.quicktodo.tasklist.presentation

import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.tasklist.domain.usecases.GetTasksUseCase
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeTask
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeTasks
import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeUpdateTask
import cl.mess.quicktodo.testcoroutinerule.TestCoroutineRule
import cl.mess.quicktodo.utils.RandomFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TaskListViewModelTest {

    @get:Rule
    val dispatcherRule = TestCoroutineRule()

    private lateinit var viewModel: TaskListViewModel
    private val getTasksUseCase: GetTasksUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = TaskListViewModel(getTasksUseCase)
    }

    @Test
    fun `Given tasks exist, When loadTasks is called, Then tasks are loaded successfully`() = runTest {
        // Given
        val taskList = makeTasks()
        coEvery { getTasksUseCase.getTasks() } returns taskList

        // When
        viewModel.loadTasks()

        // Then
        assertFalse(viewModel.isLoading.value)
        assertNull(viewModel.errorMessage.value)
        assertEquals(1, viewModel.tasks.size)

        // Verify that getTasksUseCase was called once
        coVerify { getTasksUseCase.getTasks() }
    }

    @Test
    fun `Given an error occurs, When loadTasks is called, Then error message is shown`() = runTest {
        // Given
        coEvery { getTasksUseCase.getTasks() } throws Exception("Network error")

        // When
        viewModel.loadTasks()

        // Then
        assertFalse(viewModel.isLoading.value)
        assertEquals("An error occurred while loading tasks", viewModel.errorMessage.value)
        assertTrue(viewModel.tasks.isEmpty())

        // Verify that getTasksUseCase was called
        coVerify { getTasksUseCase.getTasks() }
    }

    @Test
    fun `When addTask is called, Then task is added to the list`() {
        // Given
        val newTask = makeTask()

        // When
        viewModel.addTask(newTask)

        // Then
        assertEquals(expected = 1, actual = viewModel.tasks.size)
    }

    @Test
    fun `When removeTask is called, Then task is removed from the list`() {
        // Given
        val taskToRemove = makeTask()
        viewModel.addTask(taskToRemove)

        // When
        viewModel.removeTask(taskToRemove)

        // Then
        assertTrue(viewModel.tasks.isEmpty())
    }

    @Test
    fun `When updateTaskById is called, Then task is updated`() {
        // Given
        val originalTask = makeUpdateTask()
        val updatedTask = makeUpdateTask()
        viewModel.addTask(originalTask)

        // When
        viewModel.updateTaskById(1, updatedTask)

        // Then
        assertEquals(expected = "Updated Task", actual = viewModel.tasks[0].title)
    }
}
