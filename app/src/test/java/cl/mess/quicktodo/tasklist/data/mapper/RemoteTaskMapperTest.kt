package cl.mess.quicktodo.tasklist.data.mapper

import cl.mess.quicktodo.tasklist.factory.TaskListFactory.makeRemoteTasks
import kotlin.test.Test
import kotlin.test.assertEquals

class RemoteTaskMapperTest {

    @Test
    fun `given list of RemoteTasks when mapped then returns list of Tasks`() {
        // Given
        val remoteTasks = makeRemoteTasks()

        // When
        val result = remoteTasks.toTasks()

        // Then
        assertEquals(1, result.size)
    }
}
