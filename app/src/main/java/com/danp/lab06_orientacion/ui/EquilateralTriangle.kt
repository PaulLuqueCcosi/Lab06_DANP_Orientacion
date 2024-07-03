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
import androidx.compose.ui.graphics.TransformOrigin
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
                .size(width = 200.dp, height = 250.dp) // Tamaño del Canvas como rectángulo (300dp de ancho y 200dp de alto)
                .graphicsLayer(
                    rotationZ = if (isRotationFixed.value) rotationAngle.value else 0f,

                    transformOrigin = TransformOrigin.Center // Ajusta el punto de origen (x, y)

        )
                .background(Color.Yellow)
        ) {
            // Dibujar el triángulo isósceles más grande y más arriba
            val width = size.width
            val height = size.height
            val baseWidth = width * 0.8f // Ancho de la base (80% del ancho total)
            val baseOffset = (width - baseWidth) / 2 // Desplazamiento de la base

            // Coordenadas de los vértices del triángulo
            val point1 = Offset(x = baseOffset + baseWidth / 2, y = height * 0.0f) // Ajusta la altura del triángulo
            val point2 = Offset(x = baseOffset, y = height)
            val point3 = Offset(x = baseOffset + baseWidth, y = height)

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

            // Dibujar un círculo rojo en el centro del Canvas
            val circleRadius = 20f // Radio del círculo
            val centerX = size.width / 2 // Coordenada x del centro del Canvas
            val centerY = size.height / 2 // Coordenada y del centro del Canvas
            drawCircle(
                color = Color.Red,
                radius = circleRadius,
                center = Offset(centerX, centerY)
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
