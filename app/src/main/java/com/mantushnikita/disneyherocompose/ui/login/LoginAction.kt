package com.mantushnikita.disneyherocompose.ui.login

sealed class LoginAction {
    class OnLoginClick(
        val email:String,
        val registration: String
    ):LoginAction()
}