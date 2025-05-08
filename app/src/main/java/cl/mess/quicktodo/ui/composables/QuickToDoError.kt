package cl.mess.quicktodo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.quicktodo.R
import cl.mess.quicktodo.ui.theme.backgroundColor
import cl.mess.quicktodo.ui.theme.highlightItemColor

@Composable
fun QuickToDoError(
    onClick: () -> Unit,
    errorMessage: String = "Error",
    buttonText: String = stringResource(id = R.string.retry),
    painter: Painter = painterResource(id = R.drawable.baseline_wifi_tethering_error_24)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                colorFilter = ColorFilter.tint(Color.LightGray),
                contentDescription = "Error",
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            QuickToDoText(
                attrs = AttrsQuickToDoText(
                    text = errorMessage,
                    fontSize = 24.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = highlightItemColor),
            ) {
                QuickToDoText(
                    attrs = AttrsQuickToDoText(
                        text = buttonText,
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.offset(y = (-3).dp)
                    )
                )
            }
        }
    }
}
