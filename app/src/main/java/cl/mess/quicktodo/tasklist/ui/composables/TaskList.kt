package cl.mess.quicktodo.tasklist.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.R
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.tasklist.presentation.TaskListViewModel
import cl.mess.quicktodo.tasklist.ui.composables.taskitem.TaskItem
import cl.mess.quicktodo.ui.composables.AttrsQuickToDoText
import cl.mess.quicktodo.ui.composables.AttrsQuickToDoTextFontFamily
import cl.mess.quicktodo.ui.composables.QuickToDoAlertDialog
import cl.mess.quicktodo.ui.composables.QuickToDoModalBottomSheet
import cl.mess.quicktodo.ui.composables.QuickToDoText
import cl.mess.quicktodo.ui.theme.backgroundColor
import cl.mess.quicktodo.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
    viewModel: TaskListViewModel,
    tasks: List<Task>
) {
    val expandedUserIds = remember { mutableStateOf(setOf<Int>()) }
    val groupedTasks = tasks.groupBy { it.userId }
    var showAddTaskModal by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    var taskToEdit by remember { mutableStateOf<Pair<Int, Task>?>(null) }

    Scaffold(
        topBar = {
            QuickToDoText(
                attrs = AttrsQuickToDoText(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 32.sp,
                    color = Color.White,
                    attrsFontFamily = AttrsQuickToDoTextFontFamily(
                        resId = R.font.oswald_medium
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(all = 12.dp)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = green,
                onClick = { showAddTaskModal = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            groupedTasks.forEach { (userId, userTasks) ->
                item {
                    TaskHeader(
                        userId = userId,
                        isExpanded = userId in expandedUserIds.value,
                        onClick = {
                            expandedUserIds.value =
                                if (userId in expandedUserIds.value) {
                                    expandedUserIds.value - userId
                                } else {
                                    expandedUserIds.value + userId
                                }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
                if (userId in expandedUserIds.value) {
                    itemsIndexed(userTasks) { index, task ->
                        TaskItem(
                            task = task,
                            onDelete = { taskToDelete = it },
                            onEdit = { taskToEdit = index to it }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }

        taskToDelete?.let { task ->
            QuickToDoAlertDialog(
                onClick = {
                    viewModel.removeTask(task)
                    taskToDelete = null
                },
                onDismiss = { taskToDelete = null },
                description = "¿${stringResource(id = R.string.are_you_sure_you_want_to_delete_the_task)} ${task.title}?"
            )
        }

        taskToEdit?.let { (_, task) ->
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            var editedTitle by remember { mutableStateOf(task.title) }
            var editedCompleted by remember { mutableStateOf(task.completed) }

            QuickToDoModalBottomSheet(
                sheetState = sheetState,
                onClick = {
                    viewModel.updateTaskById(
                        taskId = task.id,
                        newTask = task.copy(
                            title = editedTitle,
                            completed = editedCompleted
                        )
                    )
                    taskToEdit = null
                },
                onDismiss = { taskToEdit = null },
                value = editedTitle,
                onValueChange = { editedTitle = it },
                checked = editedCompleted,
                onCheckedChange = { editedCompleted = it }
            )
        }

        if (showAddTaskModal) {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            var newTitle by remember { mutableStateOf("") }
            var newCompleted by remember { mutableStateOf(false) }

            QuickToDoModalBottomSheet(
                sheetState = sheetState,
                onClick = {
                    val newTask = Task(
                        id = if (tasks.isNotEmpty()) tasks.maxOf { it.id } + 1 else 1,
                        userId = 0,
                        title = newTitle,
                        completed = newCompleted
                    )
                    viewModel.addTask(newTask)
                    showAddTaskModal = false
                },
                onDismiss = { showAddTaskModal = false },
                value = newTitle,
                onValueChange = { newTitle = it },
                checked = newCompleted,
                onCheckedChange = { newCompleted = it },
                isEditMode = false
            )
        }
    }
}
