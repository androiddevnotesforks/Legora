package views.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.ApplicationColors
import utils.ApplicationIcons
import utils.ApplicationStrings

@Composable
fun HeaderComponent() {
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        ImageComponent(ApplicationIcons.LOGO_RED, ApplicationStrings.APP_LOGO, Modifier.width(60.dp).height(60.dp))
        Text(ApplicationStrings.APP_NAME, modifier = Modifier.padding(start = 20.dp), color = ApplicationColors.PRIMARY_COLOR)
    }
}