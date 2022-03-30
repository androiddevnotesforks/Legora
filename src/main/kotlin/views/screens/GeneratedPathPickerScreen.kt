package views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import utils.ApplicationColors
import utils.ApplicationIcons
import utils.ApplicationProjectsManager
import utils.ApplicationStrings
import views.common.HeaderComponent
import views.components.CircleIconComponent
import views.components.ProjectCardComponent
import java.awt.FileDialog
import java.awt.Frame
import java.io.File


@OptIn(ExperimentalUnitApi::class)
@Composable
fun GeneratedPathPickerScreen(
    projectKey: String,
    nextRoute: Int,
    prevRoute: Int,
    onRouteChangeDestination: (Int, String) -> Unit,
) {
    val selectedProjectInformation = ApplicationProjectsManager.getProjectsList().filter { it.key.equals(projectKey) }
    val generatedPath = remember { mutableStateOf("") }
    val isFileChooserOpen = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        HeaderComponent()
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(ApplicationStrings.GENERATED_PATH_TITLE, fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold)
                Text(ApplicationStrings.GENERATED_PATH_DESCRIPTION, fontSize = TextUnit(12f, TextUnitType.Sp))
                Spacer(Modifier.height(30.dp))

                Column {
                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = ApplicationColors.PRIMARY_COLOR,
                            cursorColor = ApplicationColors.PRIMARY_COLOR,
                            focusedLabelColor = ApplicationColors.PRIMARY_COLOR
                        ),
                        value = generatedPath.value,
                        label = { Text(text = "Please Enter Source Code Path") },
                        placeholder = { Text(text = "Please Enter Source Code Path") },
                        onValueChange = { newText ->
                            generatedPath.value = newText
                        },
                        enabled = false,
                        singleLine = true
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = {
                        isFileChooserOpen.value = true
                    }) {
                        Text("Pick Application Source Path")
                    }
                }
            }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Box(Modifier.fillMaxWidth(0.4f)) {
                    ProjectCardComponent(selectedProjectInformation[0], projectKey) {}
                }

                if (generatedPath.value.isNotEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        ) {
                            Row {
                                Box(modifier = Modifier.rotate(180f)) {
                                    CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Prev Page") {
                                        onRouteChangeDestination(prevRoute, generatedPath.value)
                                    }
                                }

                                Spacer(modifier = Modifier.width(20.dp))
                                CircleIconComponent(ApplicationIcons.NEXT_ARROW, "Next Page") {
                                    onRouteChangeDestination(nextRoute, generatedPath.value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isFileChooserOpen.value) {
        FileDialog(
            onCloseRequest = {
                isFileChooserOpen.value = false
                generatedPath.value = it ?: ""
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: String?) -> Unit,
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose The Source Code Path", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(File(directory + file).absolutePath)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)