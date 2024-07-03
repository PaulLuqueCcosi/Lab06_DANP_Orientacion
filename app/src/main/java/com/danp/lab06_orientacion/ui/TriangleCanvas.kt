package com.danp.lab06_orientacion.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun EquilateralTriangle(
    rotationAngles: FloatArray,
    isRotationFixed: Boolean,
    fixedRotation: Float,
    onToggleRotationFixed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    rotationZ = if (isRotationFixed) fixedRotation else Math.toDegrees(rotationAngles[0].toDouble()).toFloat()
                )
        ) {
            val width = size.width
            val height = size.height

            val point1 = Offset(x = width / 2, y = 0f)
            val point2 = Offset(x = 0f, y = height)
            val point3 = Offset(x = width, y = height)

            val trianglePath = Path().apply {
                moveTo(point1.x, point1.y)
                lineTo(point2.x, point2.y)
                lineTo(point3.x, point3.y)
                close()
            }

            drawPath(
                path = trianglePath,
                color = Color.Blue,
            )
        }

        Button(onClick = onToggleRotationFixed) {
            Text("Fix Rotation")
        }
    }
}
