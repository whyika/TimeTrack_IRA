import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.example.timetracker.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Time Tracker KMP",
        state = rememberWindowState(width = 400.dp, height = 300.dp)
    ) {
        App()
    }
}
