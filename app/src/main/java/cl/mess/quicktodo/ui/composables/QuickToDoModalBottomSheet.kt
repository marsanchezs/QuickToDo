package cl.mess.quicktodo.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.R
import cl.mess.quicktodo.ui.theme.backgroundColor
import cl.mess.quicktodo.ui.theme.controlBarColor
import cl.mess.quicktodo.ui.theme.green
import cl.mess.quicktodo.ui.theme.red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickToDoModalBottomSheet(
    sheetState: SheetState,
    value: String,
    onValueChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    isEditMode: Boolean = true
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = controlBarColor
    ) {
        var showError by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            // Header
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = if (isEditMode) Icons.Default.Edit else Icons.Default.Add,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )

                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = stringResource(id = if (isEditMode) R.string.edit_task else R.string.add_task),
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.offset(y = (-2).dp)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }

            // TextField
            TextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                    if (showError && it.isNotBlank()) showError = false
                },
                placeholder = {
                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = stringResource(id = R.string.task_description),
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp)
                    .background(backgroundColor),
                maxLines = 5,
                singleLine = false,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(
                            resId = R.font.oswald_regular,
                            weight = FontWeight.Normal
                        )
                    )
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.LightGray,
                    focusedContainerColor = backgroundColor,
                    unfocusedContainerColor = backgroundColor,
                )
            )

            // Error Text
            if (showError) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "",
                        tint = red,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 4.dp)
                    )
                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = stringResource(id = R.string.the_task_cannot_be_empty),
                            color = red,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = green
                    )
                )
                QuickToDoText(
                    attrs = AttrsQuickToDoText(
                        text = stringResource(id = if (checked) R.string.completed else R.string.pending),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDismiss) {
                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = stringResource(id = R.string.cancel),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                }

                TextButton(
                    onClick = {
                        if (value.isBlank()) {
                            showError = true
                        } else {
                            onClick()
                        }
                    }
                ) {
                    QuickToDoText(
                        attrs = AttrsQuickToDoText(
                            text = stringResource(id = R.string.save),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}
