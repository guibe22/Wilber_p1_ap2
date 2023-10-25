package com.example.wilber_p1_ap2.ui.theme.division

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
class divisionViewModel @Inject constructor(
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

    var mensaje by mutableStateOf("")

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
        validarEcuacion()
        dividiendoError = valor.isBlank() || valor.toDoubleOrNull() ?: 0.0 <= 0.0
        dividiendoLabel = if (dividiendoError) "Dividiendo requerido" else ""

    }

    fun onDivisorChanged(valor: String) {
        divisor = valor
        validarEcuacion()
        divisorError = valor.isBlank() || valor.toDoubleOrNull() ?: 0.0 <= 0.0
        divisorLabel = if (divisorError) "Divisor requerido" else ""

    }

    fun onCocienteChanged(valor: String) {
        cociente = valor
        validarEcuacion()
        cocienteError = valor.isBlank() || valor.toDoubleOrNull() == null
        cocienteLabel = if (cocienteError) "Cociente requerido" else ""

    }

    fun onResiduoChanged(valor: String) {
        residuo = valor
        validarEcuacion()
        residuoError = valor.isBlank() || valor.toDoubleOrNull() == null
        residuoLabel = if (residuoError) "Residuo requerido" else ""

    }

    fun validarEcuacion() {
        val dividendoValue = dividiendo.toDoubleOrNull() ?: 0.0
        val divisorValue = divisor.toDoubleOrNull() ?: 1.0
        val cocienteValue = cociente.toDoubleOrNull() ?: 0.0
        val residuoValue = residuo.toDoubleOrNull() ?: 0.0

        val resultadoEcuacion = dividendoValue / divisorValue
        val cocienteNum = resultadoEcuacion.toInt()
        val residuoNum = dividendoValue % divisorValue


        val cocienteValueValid = cocienteValue == cocienteNum.toDouble()
        val residuoValueValid = residuoValue == residuoNum

        if (!cocienteValueValid || !residuoValueValid) {
            divisorLabel = if (divisor.isBlank()) "Divisor Requerido" else "Divisor incorrecto"
            divisorError = true
            cocienteLabel = if (cociente.isBlank()) "Cociente Requerido" else "Cociente incorrecto"
            cocienteError = true
            residuoLabel = if (residuo.isBlank()) "Residuo Requerido" else "Residuo incorrecto"
            residuoError = true
        } else {
            divisorLabel = ""
            divisorError = false
            cocienteLabel = ""
            cocienteError = false
            residuoLabel = ""
            residuoError = false
        }
    }

    fun validar(): Boolean {
        onNombreChanged(nombre)
        onDivisorChanged(divisor)
        onDividiendoChanged(dividiendo)
        onCocienteChanged(cociente)
        onResiduoChanged(residuo)

        return nombreError || divisorError || dividiendoError || cocienteError || residuoError
    }
    fun dividirPorDiez(numero: Double): Double {
        if (numero < 1.0) {
            return numero
        }

        return dividirPorDiez(numero / 10)
    }
    val divisiones: StateFlow<List<Division>> = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
        fun save() {
           if (validar()){
               mensaje="error"
               return}
            viewModelScope.launch {
                val division = Division(
                    Nombre=nombre,
                    Divisor = divisor,
                    Dividiendo = dividiendo,
                    Cociente = cociente,
                    Residuo = residuo
                )
                mensaje="Guardado Correctamente"
                repository.save(division)
            }
            limpiar()
        }



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


       fun  delete(division:Division){
           viewModelScope.launch {
               repository.delete(division)
           }
        }


















}

