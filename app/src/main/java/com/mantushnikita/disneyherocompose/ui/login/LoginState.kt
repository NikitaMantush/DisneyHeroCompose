package com.mantushnikita.disneyherocompose.ui.login

sealed class LoginState {
    object SuccessfulLogin: LoginState()
    class Error(val error: String): LoginState()
}