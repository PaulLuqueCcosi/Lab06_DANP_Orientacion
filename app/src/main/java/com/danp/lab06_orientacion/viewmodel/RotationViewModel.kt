package com.danp.lab06_orientacion.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class RotationViewModel : ViewModel() {
    private val _isRotationFixed = MutableStateFlow(false)
    val isRotationFixed: StateFlow<Boolean> get() = _isRotationFixed

    private val _rotationAngle = MutableStateFlow(0f)
    val rotationAngle: StateFlow<Float> get() = _rotationAngle

    fun toggleRotationFixed() {
        _isRotationFixed.value = !_isRotationFixed.value
        if (_isRotationFixed.value) {
            _rotationAngle.value = Random.nextFloat() * 360
        }
        println(if (_isRotationFixed.value) "Fijado" else "Rotando")
    }
}
