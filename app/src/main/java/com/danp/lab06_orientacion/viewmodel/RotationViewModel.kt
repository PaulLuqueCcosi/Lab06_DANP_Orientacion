// RotationViewModel.kt
package com.danp.lab06_orientacion.viewmodel

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RotationViewModel(
    private val sensorManager: SensorManager
) : ViewModel() {
    private val rotationSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) // esto se puede cambiar por otro
    // https://developer.android.com/develop/sensors-and-location/sensors/sensors_position?hl=es-419


    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (event.values.size >= 4) {
                val rotationMatrix = FloatArray(9)
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                val orientationValues = FloatArray(3)
                SensorManager.getOrientation(rotationMatrix, orientationValues)

                // Calcular el ángulo de rotación en grados
                val roll1 = Math.toDegrees(orientationValues[0].toDouble()).toFloat()
                // Calcular el ángulo de rotación en grados
//                val roll1 = Math.toDegrees(orientationValues[0].toDouble()).toFloat()
                val roll2 = Math.toDegrees(orientationValues[1].toDouble()).toFloat()
                val roll3 = Math.toDegrees(orientationValues[2].toDouble()).toFloat()

//                for (i in orientationValues) {
                Log.d("VALOR1:", "" + roll1)
                Log.d("VALOR2:", "" + roll2)
                Log.d("VALOR3:", "" + roll3)
                // Actualizar el ángulo de rotación en el ViewModel
                _rotationAngle.value = roll1
                // TODO: mejorar, no funcioan bien
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    private val _isRotationFixed = MutableStateFlow(false)
    val isRotationFixed: StateFlow<Boolean> get() = _isRotationFixed

    private val _rotationAngle = MutableStateFlow(0f)
    val rotationAngle: StateFlow<Float> get() = _rotationAngle

    init {
        registerRotationSensor()
    }

    private fun registerRotationSensor() {
        rotationSensor?.also { sensor ->
            sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun toggleRotationFixed() {
        _isRotationFixed.value = !_isRotationFixed.value
        if (!_isRotationFixed.value) {
            // Detener la actualización del ángulo cuando no esté fijado
            _rotationAngle.value = 0f
        }
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(sensorEventListener)
    }
}
