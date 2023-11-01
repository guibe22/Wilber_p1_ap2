package com.example.wilber_p1_ap2.ui.theme.division

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wilber_p1_ap2.data.local.entities.Division
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DivisionScreen(
    viewModel: divisionViewModel = hiltViewModel()) {
    val divisiones by viewModel.divisiones.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = viewModel.mensaje,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Primer Parcial") }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item {
                        Registro(viewModel = viewModel)

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = "Historial de Resultados",
                            fontSize = 20.sp
                        )
                        

                        Spacer(modifier = Modifier.height(25.dp))
                    }

                    items(divisiones) { division ->
                       CardDivision(division = division, viewModel=viewModel )


                    }
                }
            }

        }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Registro(viewModel:divisionViewModel){
    
    
    TextBox(
        label = "Nombre",
        value =viewModel.nombre ,
        onValueChange = viewModel::onNombreChanged,
        isError = viewModel.nombreError,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        focusDirection = FocusDirection.Next
    )
    Spacer(modifier = Modifier.height(5.dp))
    Row {
        Column(
            modifier = Modifier.weight(1F)
        ) {
            TextBox(
                label = "Dividiendo",
                value =viewModel.dividiendo ,
                onValueChange = viewModel::onDividiendoChanged,
                isError = viewModel.dividiendoError,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                focusDirection = FocusDirection.Next
            )
            Text(
                text = viewModel.dividiendoLabel,
                color = Color.Red,
                fontSize = 10.sp
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Column(
            modifier = Modifier.weight(1F)
        ) {
            TextBox(
                label = "Divisor",
                value =viewModel.divisor ,
                onValueChange = viewModel::onDivisorChanged,
                isError = viewModel.divisorError,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                focusDirection = FocusDirection.Next
            )
            Text(
                text = viewModel.divisorLabel,
                color = Color.Red,
                fontSize = 10.sp
            )
        }


        
    }
    Row {
        Column(
            modifier = Modifier.weight(1F)
        ) {
            TextBox(
                label = "Cociente",
                value =viewModel.cociente ,
                onValueChange = viewModel::onCocienteChanged,
                isError = viewModel.cocienteError,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                focusDirection = FocusDirection.Next
            )
            Text(
                text = viewModel.cocienteLabel,
                color = Color.Red,
                fontSize = 10.sp
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Column(
            modifier = Modifier.weight(1F)
        ) {
            TextBox(
                label = "Residuo",
                value =viewModel.residuo ,
                onValueChange = viewModel::onResiduoChanged,
                isError = viewModel.residuoError,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                focusDirection = FocusDirection.Next
            )
            Text(
                text = viewModel.residuoLabel,
                color = Color.Red,
                fontSize = 10.sp
            )
        }


    }


        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                keyboardController?.hide()
                viewModel.save()
                viewModel.setMessageShown()
            })
        {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
            Text(text = "Guardar")
        }



    }



@Composable
fun CardDivision(
    division: Division,
    viewModel: divisionViewModel
){


        Text(
            text = division.Nombre,
            fontSize = 20.sp
        )
       Row(
           verticalAlignment = Alignment.CenterVertically
       ){


        Column(
            modifier = Modifier.weight(8F)
        ) {
        Row(

        ){
            Text(
                text = "Dividiendo: "+division.Dividiendo,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "Divisor: "+division.Divisor,
                fontSize = 15.sp
            )
        }
            Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                text = "Cociente: "+division.Cociente,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Residuo: "+division.Residuo,
                fontSize = 15.sp
            )
        }
        }

           Column(
               modifier = Modifier
                   .weight(2F)
                   .padding(10.dp)
           ) {

                   IconButton(
                       onClick = {
                           viewModel.delete(division)
                       },
                       modifier = Modifier
                           .fillMaxWidth()
                           .aspectRatio(1f)
                           .border(1.dp, Color.Red)
                           .size(10.dp)
                           .padding(10.dp)
                   ) {
                       Icon(imageVector = Icons.Default.Close, contentDescription = "Eliminar", tint = Color.Red)
                   }
           }

    }
    Divider()
    Spacer(modifier = Modifier.height(25.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(
    label:String,
    value: String,
    onValueChange:(String)->Unit,
    isError:Boolean,
    keyboardType:KeyboardType,
    imeAction: ImeAction,
    focusDirection:FocusDirection
){
    
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = label)} ,
        singleLine = true,
        maxLines=1,
        value =  value,
        onValueChange =  onValueChange,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType =  keyboardType,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions{
            focusManager.moveFocus(focusDirection) 
            
        }
    )
}





