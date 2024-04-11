package com.mantushnikita.disneyherocompose.ui.singup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mantushnikita.disneyherocompose.navigation.LOGIN_SCREEN
import com.mantushnikita.disneyherocompose.navigation.SINGUP_SCREEN

@Composable
fun SingUpScreen(
    navigationController: NavHostController,
    viewModel: SingUpViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    viewModel.state.observeAsState().value?.let {state ->
        when(state){
            is SingUpState.SuccessfulRegistration ->{
                navigationController.navigateUp()
            }
            is SingUpState.Error ->{
                Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
            }
        }
    }
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email icon"
                )
            },
            singleLine = true,
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password icon"
                )
            },
            singleLine = true,
        )
        Button(onClick = {
            viewModel.processAction(SingUpAction.OnSingUpClick(email, password))
        }) {
            Text(text = "Register")
        }
        Button(onClick = {
            navigationController.navigate(LOGIN_SCREEN)
        }) {
            Text(text = "Login")
        }
    }
}
