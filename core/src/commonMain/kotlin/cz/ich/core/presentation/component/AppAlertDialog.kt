package cz.ich.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cryptomotivation.core.generated.resources.Res
import cryptomotivation.core.generated.resources.dialog_btn_close
import org.jetbrains.compose.resources.stringResource

/**
 * Application alert dialog.
 *
 * @param text Text to display in alert dialog.
 * @param modifier The modifier to be applied on this layout.
 * @param onCloseClicked Callback for close button click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAlertDialog(
    text: String,
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit = {},
) {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = text)
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = onCloseClicked,
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text(stringResource(Res.string.dialog_btn_close))
                }
            }
        }
    }
}