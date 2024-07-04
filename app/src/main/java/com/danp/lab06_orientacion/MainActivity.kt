package com.danp.lab06_orientacion

import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danp.lab06_orientacion.ui.MyApp
import com.danp.lab06_orientacion.viewmodel.RotationViewModel

class MainActivity : ComponentActivity() {

//    val viewmodel = RotationViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(RotationViewModel((getSystemService(SENSOR_SERVICE) as SensorManager)!!))
        }
    }
}
