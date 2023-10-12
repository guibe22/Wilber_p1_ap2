package com.example.wilber_p1_ap2.ui.theme.Division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wilber_p1_ap2.data.local.entities.Division
import com.example.wilber_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.xml.sax.Parser
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val repository: DivisionRepository
) : ViewModel() {
    var nombre  by mutableStateOf("")
    var dividendo by mutableStateOf("")
    var divisor by mutableStateOf("")
    var cociente by mutableStateOf("")
    var residuo by mutableStateOf("")

    var nombreError by mutableStateOf(false)
    var dividendoError by mutableStateOf(false)
    var divisorError by mutableStateOf(false)
    var cocienteError by mutableStateOf(false)
    var residuoError by mutableStateOf(false)

    var dividendoLabel by mutableStateOf("probando")
    var divisorLabel by mutableStateOf("probando")
    var cocienteLabel  by mutableStateOf("probando")
    var residuoLabel by mutableStateOf("probando")

    fun nombreOnchange(Valor:String){
        nombreError= Valor.isBlank()
    }
    fun dividiendoOnChange(valor:String){
        if (valor.isNullOrBlank()) {
            dividendoError =true
            dividendoLabel="Cociente  Requerido"
            return
        }
        else {
            if (valor.toInt() <= 0) {
                dividendoError = true
                divisorLabel = "Diviendo es Requerido"
            } else {
                dividendoError = false
                divisorLabel = ""
            }
        }
    }

    fun divisorOnChage(valor:String){
        if (valor.isNullOrBlank()) {
            divisorError =true
            divisorLabel="Cociente  Requerido"
            return
        }
        else {
            if (valor.toInt() <= 0) {
                divisorError = true
                divisorLabel = "Divisor Requerido"
            } else if ((cociente.toInt() + residuo.toInt()) != (dividendo.toInt() / divisor.toInt())) {
                divisorError = true
                divisorLabel = "Divisor Incorrecto"
            } else {
                divisorError = false
                divisorLabel = ""
            }
        }
    }

    fun cocienteOnChage(valor:String){
        if (valor.isNullOrBlank()) {
            cocienteError =true
            cocienteLabel="Cociente  Requerido"
            return
        }
        else {
            if (valor.toInt() <= 0) {
                cocienteError = true
                cocienteLabel = "Cociente Requerido"
            } else if ((cociente.toInt() + residuo.toInt()) != (dividendo.toInt() / divisor.toInt())) {
                cocienteError = true
                cocienteLabel = "Divisor Incorrecto"
            } else {
                cocienteError = false
                cocienteLabel = ""
            }
        }
    }

    fun residuoOnChage(valor:String){
        if (valor.isNullOrBlank()) {
            residuoError =true
            residuoLabel="Residuo  Requerido"
            return
        }
        else{

        if(valor.toInt()<=0) {
            residuoError =true
            residuoLabel="Residuo  Requerido"
        }
        else if((cociente.toInt()+residuo.toInt())!=(dividendo.toInt()/divisor.toInt())){
            residuoError =true
            residuoLabel="Residuo Incorrecto"
        }
        else {
            residuoError =false
            residuoLabel=""
        }
        }
    }
    val divisiones: StateFlow<List<Division>> = repository.getAll()
    .stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList()
    )

    fun save() {
        if(validar()){
            return
        }
        viewModelScope.launch {
            val division = Division(
                Divisor = divisor,
                Dividiendo = dividendo,
                Cociente = cociente,
                Residuo = residuo,
                nombre = nombre
            )
            repository.save(division)
            limpiar()
        }
    }
    fun validar(): Boolean {
        nombreOnchange(nombre)
        dividiendoOnChange(dividendo)
        divisorOnChage(divisor)
        residuoOnChage(residuo)
        cocienteOnChage(cociente)
        return dividendoError && divisorError && nombreError && cocienteError && residuoError
    }

    fun limpiar(){
        dividendo =""
        divisor =""
        cociente =""
        residuo =""
    }

    fun delete( division: Division){
        viewModelScope.launch {
            repository.delete(division)

        }
    }

}
