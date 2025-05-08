package cl.mess.quicktodo.tasklist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import cl.mess.quicktodo.tasklist.presentation.TaskListViewModel
import cl.mess.quicktodo.tasklist.ui.composables.TaskList
import cl.mess.quicktodo.ui.composables.QuickToDoError
import cl.mess.quicktodo.ui.composables.QuickToDoLoading

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = hiltViewModel()) {
    val tasks = viewModel.tasks
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    when {
        isLoading -> {
            QuickToDoLoading()
        }

        errorMessage != null -> {
            QuickToDoError(
                errorMessage = errorMessage ?: "Error",
                onClick = { viewModel.loadTasks() }
            )
        }

        else -> {
            TaskList(
                viewModel = viewModel,
                tasks = tasks
            )
        }
    }
}
