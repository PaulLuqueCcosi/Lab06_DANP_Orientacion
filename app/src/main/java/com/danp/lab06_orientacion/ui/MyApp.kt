package com.danp.lab06_orientacion.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danp.lab06_orientacion.viewmodel.RotationViewModel

@Composable
fun MyApp(viewModel: RotationViewModel) {
    EquilateralTriangle(viewModel)

}
