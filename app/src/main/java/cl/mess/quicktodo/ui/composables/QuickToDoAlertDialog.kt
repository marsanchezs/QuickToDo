package cl.mess.quicktodo.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.R
import cl.mess.quicktodo.ui.theme.controlBarColor

@Composable
fun QuickToDoAlertDialog(
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    title: String = stringResource(id = R.string.confirm_deletion),
    description: String = stringResource(id = R.string.are_you_sure_you_want_to_delete_the_task),
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Warning",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )

                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = title,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.offset(y = (-2).dp)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }
        },
        text = {
            QuickToDoText(
                attrs = AttrsQuickToDoText(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        },
        confirmButton = {
            TextButton(onClick = onClick) {
                QuickToDoText(
                    attrs = AttrsQuickToDoText(
                        text = stringResource(id = R.string.yes),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                QuickToDoText(
                    attrs = AttrsQuickToDoText(
                        text = stringResource(id = R.string.no),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
        },
        containerColor = controlBarColor,
        shape = RoundedCornerShape(16.dp)
    )
}
