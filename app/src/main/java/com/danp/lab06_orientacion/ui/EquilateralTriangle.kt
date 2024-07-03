package com.danp.lab06_orientacion.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Añade padding para separación del borde de la pantalla
        verticalArrangement = Arrangement.SpaceBetween, // Alinea los elementos hacia abajo
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Orientacion del triangulo",
            modifier = Modifier
                .background(Color.Yellow)
                .padding(8.dp)
        )

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(
                    rotationZ = if (isRotationFixed.value) rotationAngle.value else 0f
                )
                .background(Color.Yellow)

        ) {
            // Dibujar el triángulo equilátero
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


        // Botón al final de la pantalla
        Button(
            onClick = { viewModel.toggleRotationFixed() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(Color.Cyan)

        ) {
            Text(if (viewModel.isRotationFixed.value) "Fijado" else "Rotando")
        }
    }
}
