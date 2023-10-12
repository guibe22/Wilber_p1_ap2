package com.example.wilber_p1_ap2.ui.theme.Division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wilber_p1_ap2.data.local.entities.Division
import com.example.wilber_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val repository: DivisionRepository
) : ViewModel() {
    var nombre by mutableStateOf("")
    var nombreLabel by mutableStateOf("")
    var nombreError by mutableStateOf(false)


    var dividiendo by mutableStateOf("")
    var dividiendoLabel by mutableStateOf("")
    var dividiendoError by mutableStateOf(false)

    var divisor by mutableStateOf("")
    var divisorLabel by mutableStateOf("")
    var divisorError by mutableStateOf(false)

    var cociente by mutableStateOf("")
    var cocienteLabel by mutableStateOf("")
    var cocienteError by mutableStateOf(false)

    var residuo by mutableStateOf("")
    var residuoLabel by mutableStateOf("")
    var residuoError by mutableStateOf(false)

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    fun onNombreChanged(valor: String) {
        nombre= valor
        nombreError = valor.isBlank();
    }

    fun onDividiendoChanged(valor: String) {
        dividiendo = valor
        dividiendoError = valor.isBlank() || valor.toDoubleOrNull() ?: 0.0 <= 0.0
        dividiendoLabel = if (dividiendoError) "Dividiendo requerido" else ""
        validarEcuacion()
    }

    fun onDivisorChanged(valor: String) {
        divisor = valor
        divisorError = valor.isBlank() || valor.toDoubleOrNull() ?: 0.0 <= 0.0
        divisorLabel = if (divisorError) "Divisor requerido" else ""
        validarEcuacion()
    }

    fun onCocienteChanged(valor: String) {
        cociente = valor
        cocienteError = valor.isBlank() || valor.toDoubleOrNull() == null
        cocienteLabel = if (cocienteError) "Cociente requerido" else ""
        validarEcuacion()
    }

    fun onResiduoChanged(valor: String) {
        residuo = valor
        residuoError = valor.isBlank() || valor.toDoubleOrNull() == null
        residuoLabel = if (residuoError) "Residuo requerido" else ""
        validarEcuacion()
    }

    fun validarEcuacion() {
        val dividendoValue = dividiendo.toDoubleOrNull() ?: 0.0
        val divisorValue = divisor.toDoubleOrNull() ?: 1.0
        val cocienteValue = cociente.toDoubleOrNull() ?: 0.0
        val residuoValue = residuo.toDoubleOrNull() ?: 0.0

        val resultadoEcuacion = dividendoValue / divisorValue
        val sumaCocienteResiduo = cocienteValue + residuoValue

        val ecuacionError = Math.abs(resultadoEcuacion - sumaCocienteResiduo) > 0.0001

        dividiendoLabel = if (dividiendoError) "Dividiendo requerido" else ""
        divisorLabel = if (divisorError) "Divisor requerido" else ""
        cocienteLabel = if (cocienteError) "Cociente requerido" else ""
        residuoLabel = if (residuoError) "Residuo requerido" else ""

        if (ecuacionError) {
            divisorLabel = "Divisor incorrecto"
            cocienteLabel = "Cociente incorrecto"
            residuoLabel = "residuo incorrecto"
        }


        val divisiones: StateFlow<List<Division>> = repository.getAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
        fun limpiar(){
            nombre=""
            divisor=""
            dividiendo=""
            residuo=""
            cociente=""

            nombreLabel=""
            divisorLabel=""
            dividiendoLabel=""
            residuoLabel=""
            cocienteLabel=""

            nombreError = false
            divisorError = false
            dividiendoError = false
            residuoError = false
            cocienteError = false

        }
        fun save() {
            viewModelScope.launch {
                val division = Division(
                    Nombre=nombre,
                    Divisor = divisor,
                    Dividiendo = dividiendo,
                    Cociente = cociente,
                    Residuo = residuo
                )
                repository.save(division)
            }
            limpiar()
        }

       fun  delete(division:Division){
           viewModelScope.launch {
               repository.save(division)
           }
        }






    }










}