package views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import models.ProjectInformationItem
import utils.ApplicationColors

@Composable
fun TextInputComponent(item: ProjectInformationItem) {
    var text by remember { mutableStateOf(TextFieldValue(item.selectedValue)) }
    Column {
        Spacer(Modifier.padding(top = 20.dp))
        Text(item.title)
        Spacer(Modifier.padding(top = 10.dp))
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = ApplicationColors.PRIMARY_COLOR,
                cursorColor = ApplicationColors.PRIMARY_COLOR,
                focusedLabelColor = ApplicationColors.PRIMARY_COLOR
            ),
            value = text,
            label = { Text(text = item.hint) },
            placeholder = { Text(text = item.hint) },
            onValueChange = { newText ->
                text = newText
                item.selectedValue = newText.text
            }
        )
        Spacer(Modifier.padding(top = 20.dp))
    }
}
