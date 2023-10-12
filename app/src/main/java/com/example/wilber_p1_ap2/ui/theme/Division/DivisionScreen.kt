package com.example.wilber_p1_ap2.ui.theme.Division

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wilber_p1_ap2.data.local.Dao.DivisionDao
import com.example.wilber_p1_ap2.data.local.entities.Division
import com.example.wilber_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DivisionScreen(
    viewModel: DivisionViewModel= hiltViewModel()
) {

    Scaffold(
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
                }
                items(viewModel.Divisiones) {

                        
                }
            }
        }

    }
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
 fun Registro(viewModel: DivisionViewModel){

         Card(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(16.dp)
         ) {
             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(16.dp)
             ) {

                 Textbox(
                     label = "Nombre",
                     value = viewModel.nombre,
                     onValueChange = viewModel::nombreOnchange,
                     isError = viewModel.cocienteError,
                     FocusDirection =FocusDirection.Next ,
                     keyboardType =KeyboardType.Number ,
                     imeAction = ImeAction.Next
                 )

                Row{
                    Column {
                        Textbox(
                            label = "Divisor",
                            value = viewModel.divisor,
                            onValueChange = viewModel::divisorOnChage,
                            isError = viewModel.divisorError,
                            FocusDirection =FocusDirection.Next ,
                            keyboardType =KeyboardType.Number ,
                            imeAction = ImeAction.Next
                        )
                        Text(
                            text = viewModel.divisorLabel,
                            style = MaterialTheme.typography.displaySmall,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Column {
                        Textbox(
                            label = "Dividendo",
                            value = viewModel.dividendo,
                            onValueChange = viewModel::dividiendoOnChange,
                            isError = viewModel.dividendoError,
                            FocusDirection =FocusDirection.Next ,
                            keyboardType =KeyboardType.Number ,
                            imeAction = ImeAction.Next
                        )
                        Text(
                            text = viewModel.divisorLabel,
                            style = MaterialTheme.typography.displaySmall,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                 Row{
                     Column {
                         Textbox(
                             label = "Cociente",
                             value = viewModel.cociente,
                             onValueChange = viewModel::cocienteOnChage,
                             isError = viewModel.cocienteError,
                             FocusDirection =FocusDirection.Next ,
                             keyboardType =KeyboardType.Number ,
                             imeAction = ImeAction.Next
                         )
                         Text(
                             text = viewModel.divisorLabel,
                             style = MaterialTheme.typography.displaySmall,
                             modifier = Modifier.weight(1f)
                         )
                     }
                     Column {
                         Textbox(
                             label = "Dividendo",
                             value = viewModel.dividendo,
                             onValueChange = viewModel::dividiendoOnChange,
                             isError = viewModel.dividendoError,
                             FocusDirection =FocusDirection.Next ,
                             keyboardType =KeyboardType.Number ,
                             imeAction = ImeAction.Done
                         )
                         Text(
                             text = viewModel.divisorLabel,
                             style = MaterialTheme.typography.displaySmall,
                             modifier = Modifier.weight(1f)
                         )
                     }
                 }

                 val keyboardController = LocalSoftwareKeyboardController.current
                 OutlinedButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {
                                     keyboardController?.hide()
                                     viewModel.save()

                                 })
                             {
                                 Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
                                 Text(text = "Guardar")
                             }
             }
         }
     }

@Composable
fun card(
    division: Division,
    delete: (Division) -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                    text = "Nombre: " +division.nombre,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(1f)
                )
             Row {
                 Text(
                     text = "Divisor: "+ division.Divisor.toString(),
                     style = MaterialTheme.typography.displaySmall,
                     modifier = Modifier.weight(1f)
                 )
                 Text(
                     text = "Dividiendo: "+ division.Dividiendo.toString(),
                     style = MaterialTheme.typography.displaySmall,
                     modifier = Modifier.weight(1f)
                 )
             }
            Row {
                Text(
                    text = "Cociente: "+ division.Cociente.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Residuo : "+ division.Residuo.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                delete(division)

                            })
                        {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Eliminar")
                            Text(text = "Eliminar")
                        }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Textbox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    FocusDirection: FocusDirection,
    keyboardType:  KeyboardType,
    imeAction: ImeAction

){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value =value,
        onValueChange = onValueChange ,
        label = {Text(text = "Nombre")},
        singleLine = true,
        isError = isError,
        keyboardOptions =KeyboardOptions(
            keyboardType =  keyboardType,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions{
            focusManager.moveFocus( FocusDirection)
            if (imeAction == ImeAction.Done) {
                keyboardController?.hide()
            }
        }


    )


}



@Composable
fun card(
    division: Division
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Añade margen alrededor de la tarjeta
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Añade margen interno en la tarjeta
        ) {
            Text(
                    text = "letra grande",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(1f)
                )
            Text(
                text = "letra grande",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.weight(1f)
            )

        }
    }
}

