package com.usnine.prescriptionapp.ui.components


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.usnine.prescriptionapp.ui.theme.HintGray
import com.usnine.prescriptionapp.ui.theme.PrescriptionAppTheme

@Composable
fun Text(text: String, type: TextType, modifier: Modifier = Modifier, color: Color? = null, textAlign: TextAlign = TextAlign.Center) {
    val style = when (type) {
        TextType.HEADER -> MaterialTheme.typography.headlineMedium
        TextType.TITLE_LARGE -> MaterialTheme.typography.titleLarge
        TextType.TITLE_MEDIUM -> MaterialTheme.typography.titleMedium
        TextType.TITLE_SMALL -> MaterialTheme.typography.titleSmall
        TextType.BODY_LARGE -> MaterialTheme.typography.bodyLarge
        TextType.BODY_MEDIUM -> MaterialTheme.typography.bodyMedium
        TextType.BODY_SMALL -> MaterialTheme.typography.bodySmall
        TextType.HINT_LARGE -> MaterialTheme.typography.labelLarge
        TextType.HINT_MEDIUM -> MaterialTheme.typography.labelMedium
        TextType.HINT_SMALL -> MaterialTheme.typography.labelSmall
    }
    val color = when {
        color != null -> color
        type == TextType.HEADER -> MaterialTheme.colorScheme.primary
        type == TextType.TITLE_LARGE -> MaterialTheme.colorScheme.onBackground
        type == TextType.TITLE_MEDIUM -> MaterialTheme.colorScheme.onBackground
        type == TextType.TITLE_SMALL -> MaterialTheme.colorScheme.onBackground
        type == TextType.BODY_LARGE -> MaterialTheme.colorScheme.onBackground
        type == TextType.BODY_MEDIUM -> MaterialTheme.colorScheme.onBackground
        type == TextType.BODY_SMALL -> MaterialTheme.colorScheme.onBackground
        type == TextType.HINT_LARGE -> HintGray
        type == TextType.HINT_MEDIUM -> HintGray
        type == TextType.HINT_SMALL -> HintGray
        else -> MaterialTheme.colorScheme.onBackground
    }
    Text(
        modifier = modifier,
        text = text,
        style = style,
        textAlign = textAlign,
        color = color
    )
}

enum class TextType {
    HEADER,
    TITLE_LARGE,
    TITLE_MEDIUM,
    TITLE_SMALL,
    BODY_LARGE,
    BODY_MEDIUM,
    BODY_SMALL,
    HINT_LARGE,
    HINT_MEDIUM,
    HINT_SMALL
}


@Preview(showBackground = true)
@Composable
private fun HeaderTextPreview() {
    PrescriptionAppTheme {

    }
}
