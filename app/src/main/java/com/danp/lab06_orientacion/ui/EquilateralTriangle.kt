package com.danp.lab06_orientacion.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.danp.lab06_orientacion.viewmodel.RotationViewModel

@Composable
fun EquilateralTriangle(viewModel: RotationViewModel) {
    val isRotationFixed = viewModel.isRotationFixed.collectAsState()
    val rotationAngle = viewModel.rotationAngle.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    rotationZ = if (isRotationFixed.value) rotationAngle.value else 0f
                )
        ) {
            // Calculate the size and position of the triangle
            val width = size.width
            val height = size.height

            // Calculate the three points of the equilateral triangle
            val point1 = Offset(x = width / 2, y = 0f)
            val point2 = Offset(x = 0f, y = height)
            val point3 = Offset(x = width, y = height)

            // Create a Path and draw the triangle
            val trianglePath = Path().apply {
                moveTo(point1.x, point1.y)
                lineTo(point2.x, point2.y)
                lineTo(point3.x, point3.y)
                close()
            }

            // Draw the Path on the Canvas
            drawPath(
                path = trianglePath,
                color = Color.Blue,
            )
        }


    }
}
