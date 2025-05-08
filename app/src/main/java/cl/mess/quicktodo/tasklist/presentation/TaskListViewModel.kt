package cl.mess.quicktodo.tasklist.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.tasklist.domain.usecases.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> = _tasks

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _tasks.clear()
                _tasks.addAll(getTasksUseCase.getTasks())
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Ocurrió un error al cargar las tareas"
                println("Error al cargar tareas: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }


        /*viewModelScope.launch {
            if (_tasks.isEmpty()) {
                _isLoading.value = true
                _tasks.addAll(getTasksUseCase.getTasks())
                _isLoading.value = false
            }
        }*/
    }

    fun addTask(task: Task) {
        _tasks.add(element = task)
    }

    fun removeTask(task: Task) {
        _tasks.remove(element = task)
    }

    fun updateTaskById(
        taskId: Int,
        newTask: Task
    ) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = newTask
        }
    }
}
