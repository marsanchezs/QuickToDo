package cl.mess.quicktodo.tasklist.ui.composables.taskitem

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cl.mess.quicktodo.R
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.ui.theme.controlBarColor

@Composable
fun TaskItemDropDownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onEdit: (Task) -> Unit,
    task: Task,
    onDelete: (Task) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.background(controlBarColor)
    ) {
        TaskItemDropDownMenuItem(
            text = stringResource(id = R.string.edit),
            onClick = {
                onDismiss()
                onEdit(task)
            },
            imageVector = Icons.Default.Edit
        )

        TaskItemDropDownMenuItem(
            text = stringResource(id = R.string.delete),
            onClick = {
                onDismiss()
                onDelete(task)
            },
            imageVector = Icons.Default.Delete
        )
    }
}
