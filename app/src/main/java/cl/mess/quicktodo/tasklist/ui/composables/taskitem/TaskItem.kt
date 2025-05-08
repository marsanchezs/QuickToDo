package cl.mess.quicktodo.tasklist.ui.composables.taskitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.tasklist.domain.model.Task
import cl.mess.quicktodo.ui.composables.AttrsQuickToDoText
import cl.mess.quicktodo.ui.composables.QuickToDoText
import cl.mess.quicktodo.ui.theme.green
import cl.mess.quicktodo.ui.theme.itemColor
import cl.mess.quicktodo.ui.theme.red

@Composable
fun TaskItem(
    task: Task,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(itemColor)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (task.completed) Icons.Default.CheckCircle else Icons.Default.Warning,
            contentDescription = if (task.completed) "Completed" else "Pending",
            tint = if (task.completed) green else red,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        QuickToDoText(
            attrs = AttrsQuickToDoText(
                text = task.title,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
            )
        )

        Box {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "More options"
                )
            }

            TaskItemDropDownMenu(
                expanded = expanded,
                onDismiss = { expanded = false },
                onEdit = onEdit,
                task = task,
                onDelete = onDelete
            )
        }
    }
}
