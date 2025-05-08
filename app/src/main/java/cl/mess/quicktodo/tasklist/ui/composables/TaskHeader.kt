package cl.mess.quicktodo.tasklist.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.R
import cl.mess.quicktodo.ui.composables.AttrsQuickToDoText
import cl.mess.quicktodo.ui.composables.QuickToDoText
import cl.mess.quicktodo.ui.theme.itemColor

@Composable
fun TaskHeader(
    userId: Int,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(itemColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuickToDoText(
            attrs = AttrsQuickToDoText(
                text = "${stringResource(id = R.string.user)} $userId",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
        )
        Icon(
            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            tint = Color.White,
            contentDescription = if (isExpanded) "Collapse" else "Expand"
        )
    }
}
