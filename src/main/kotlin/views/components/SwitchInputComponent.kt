package views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import models.ProjectInformationItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import utils.ApplicationColors

@Composable
fun SwitchInputComponent(item: ProjectInformationItem) {
    val itemSelected = remember { mutableStateOf(item.selectedValue.isEmpty() || item.selectedValue.equals("enabled")) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.padding(top = 20.dp))
        Text(item.title)
        Spacer(Modifier.padding(top = 10.dp))
        Row {
            RadioButton(itemSelected.value, onClick = {
                item.selectedValue = "enabled"
                itemSelected.value = item.selectedValue.isEmpty() || item.selectedValue.equals("enabled")
            }, colors = RadioButtonDefaults.colors(ApplicationColors.PRIMARY_COLOR))

            Spacer(Modifier.width(10.dp))
            Text("Enabled", textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically))

            Spacer(Modifier.width(20.dp))

            RadioButton(!itemSelected.value, onClick = {
                item.selectedValue = "disabled"
                itemSelected.value = !item.selectedValue.equals("disabled")
            }, colors = RadioButtonDefaults.colors(ApplicationColors.PRIMARY_COLOR))
            Spacer(Modifier.width(10.dp))
            Text("Disabled", textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}