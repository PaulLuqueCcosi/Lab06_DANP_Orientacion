package com.danp.lab06_orientacion.ui

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.danp.lab06_orientacion.viewmodel.RotationViewModel

@Composable
fun MyApp(viewModel: RotationViewModel) {
//    MaterialTheme {
        EquilateralTriangle(viewModel)
        Button(onClick = { viewModel.toggleRotationFixed() }) {
            Text(if (viewModel.isRotationFixed.value) "Fijado" else "Rotando")
//        }
    }
}
