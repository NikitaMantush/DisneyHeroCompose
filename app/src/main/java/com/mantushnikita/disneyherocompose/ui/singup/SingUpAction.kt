package com.mantushnikita.disneyherocompose.ui.singup

import com.mantushnikita.disneyherocompose.ui.login.LoginAction

sealed class SingUpAction {
    class OnSingUpClick(
        val email:String,
        val registration: String
    ): SingUpAction()
}