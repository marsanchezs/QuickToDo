package cl.mess.quicktodo.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import cl.mess.quicktodo.R

@Composable
fun QuickToDoText(attrs: AttrsQuickToDoText) {
    Text(
        text = attrs.text,
        color = attrs.color,
        modifier = attrs.modifier,
        fontSize = attrs.fontSize,
        textAlign = attrs.textAlign,
        letterSpacing = attrs.letterSpacing,
        lineHeight = attrs.lineHeight,
        fontFamily = FontFamily(
            Font(
                resId = attrs.attrsFontFamily.resId,
                weight = attrs.attrsFontFamily.weight
            )
        ),
        fontWeight = attrs.fontWeight,
        textDecoration = attrs.textDecoration,
        overflow = attrs.overflow,
        maxLines = attrs.maxLines
    )
}

data class AttrsQuickToDoText(
    val modifier: Modifier = Modifier,
    val text: String,
    val textAlign: TextAlign? = null,
    val fontWeight: FontWeight? = null,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val attrsFontFamily: AttrsQuickToDoTextFontFamily = AttrsQuickToDoTextFontFamily(),
    val textDecoration: TextDecoration = TextDecoration.None,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val color: Color = Color.Unspecified,
    val overflow: TextOverflow = TextOverflow.Clip,
    val maxLines: Int = Int.MAX_VALUE
)

data class AttrsQuickToDoTextFontFamily(
    val resId: Int = R.font.oswald_regular,
    val weight: FontWeight = FontWeight.Normal
)
