package cz.ich.cryptomotivation.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cryptomotivation.composeapp.generated.resources.Res
import cryptomotivation.composeapp.generated.resources.kavoon_regular
import cryptomotivation.composeapp.generated.resources.open_sans_condensed_bold
import cryptomotivation.composeapp.generated.resources.open_sans_condensed_extra_bold
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
    val kavoonFontFamily = FontFamily(
        Font(Res.font.kavoon_regular, FontWeight.Normal),
    )

    val openSansFontFamily = FontFamily(
        Font(Res.font.open_sans_condensed_bold, FontWeight.Bold),
        Font(Res.font.open_sans_condensed_extra_bold, FontWeight.ExtraBold),
    )

    return Typography(
        headlineLarge = TextStyle(
            fontFamily = openSansFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        ),
        titleLarge = TextStyle(
            fontFamily = kavoonFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        ),
        titleMedium = TextStyle(
            fontFamily = kavoonFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = openSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        bodySmall = TextStyle(
            fontFamily = openSansFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp
        ),
    )

}