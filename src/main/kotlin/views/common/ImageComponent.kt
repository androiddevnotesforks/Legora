package views.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource

@Composable
fun ImageComponent(path: String, description: String, modifier: Modifier) {
    when (path.contains("svg")) {
        true -> Image(painterResource(path), description, alignment = Alignment.Center, modifier = modifier)
        false -> {
            val bitmap = useResource(path) { loadImageBitmap(it) }
            Image(
                bitmap = bitmap,
                description,
                alignment = Alignment.Center,
                modifier = modifier
            )
        }
    }
}
