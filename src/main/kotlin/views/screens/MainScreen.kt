package views.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen() {

    }
}

@Composable
fun MainScreen(onNextScreenRequest: () -> Unit) {
    Text("Main Screen")
}