package com.cattailsw.burninreconditioner

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ColorViewModel : ViewModel() {

    private val colors = listOf(Color.Red, Color.Green, Color.Blue, Color(1f, 1f, 0f), Color(0f, 1f, 1f), Color(1f, 0f, 1f), Color.White, Color.Black)
    private val _currentColor = mutableStateOf(colors[0])
    val currentColor: State<Color> = _currentColor

    private var colorIndex = 0

    init {
        // Automatically switch colors every 3 seconds
        viewModelScope.launch {
            while (true) {
                delay(3000)
                randomNextColor()
            }
        }
    }

    // Function to change to the next color
    fun nextColor() {
        colorIndex = (colorIndex + 1) % colors.size
        _currentColor.value = colors[colorIndex]
    }

    fun randomNextColor() {
        val nextColorIndex = Random.nextInt(colors.size)
        if (nextColorIndex != colorIndex) {
            colorIndex = nextColorIndex
            _currentColor.value = colors[colorIndex]
        }
    }
}
