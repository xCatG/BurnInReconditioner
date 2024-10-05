package com.cattailsw.burninreconditioner

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.cattailsw.burninreconditioner.ui.theme.BurnInReconditionerTheme

class MainActivity : ComponentActivity() {
    private val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setFullScreen()

        setContent {
            BurnInReconditionerTheme {
                FullScreenColorSwitcher(
                    backgroundColor = colorViewModel.currentColor.value,
                    clickAction = colorViewModel::nextColor)
            }
        }
    }

    private fun setFullScreen() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

@Composable
fun FullScreenColorSwitcher(
    backgroundColor: Color,
    clickAction: (() -> Unit)? = null,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor)
        .clickable {
            // tap to change color
            clickAction?.invoke()
        },
        contentAlignment = Alignment.Center) {
        // nothing, background should just change
    }
}
