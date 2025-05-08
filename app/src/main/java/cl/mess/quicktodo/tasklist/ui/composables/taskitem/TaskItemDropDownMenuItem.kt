package cl.mess.quicktodo.tasklist.ui.composables.taskitem

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.ui.composables.AttrsQuickToDoText
import cl.mess.quicktodo.ui.composables.QuickToDoText

@Composable
fun TaskItemDropDownMenuItem(
    text: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            QuickToDoText(
                attrs = AttrsQuickToDoText(
                    text = text,
                    fontSize = 16.sp,
                    color = Color.White,
                )
            )
        },
        onClick = onClick,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                tint = Color.White,
                contentDescription = "Delete"
            )
        }
    )
}
