package cl.mess.quicktodo.tasklist.domain.mapper

import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeRemoteTasks
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskListMapperTest {

    private lateinit var mapper: TaskListMapper

    @Before
    fun setUp() {
        mapper = TaskListMapper()
    }

    @Test
    fun `given a list of RemoteTask when toTasks is called then it returns a mapped list of Task`() {
        // Given
        val remoteTasks = makeRemoteTasks()

        // When
        val result = with(receiver = mapper) { remoteTasks.toTasks() }

        // Then
        assertEquals(expected = remoteTasks.size, actual = result.size)
    }
}
