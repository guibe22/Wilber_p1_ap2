package com.example.wilber_p1_ap2.ui.theme.Division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wilber_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val repository: DivisionRepository
) : ViewModel() {
    var dividendo by mutableStateOf(0)
    var divisor by mutableStateOf(0)
    var cociente by mutableStateOf(0)
    var Residuo by mutableStateOf(0)

    var dividendoError by mutableStateOf(false)
    var divisorError by mutableStateOf(false)
    var cocienteError by mutableStateOf(false)
    var ResiduoError by mutableStateOf(false)

    var dividendoLabel by mutableStateOf("Dividiendo Requerido")
    var divisorLabel by mutableStateOf("")
    var cocienteLabel  by mutableStateOf("")
    var ResiduoLabel by mutableStateOf("")


    fun dividiendoOnChange(valor:Int){

        if(valor<=0){
            dividendoError=true
        }
        else{
            dividendoError=true
        }
    }





}
