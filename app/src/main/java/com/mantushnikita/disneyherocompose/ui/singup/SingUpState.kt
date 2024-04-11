package com.mantushnikita.disneyherocompose.ui.singup

sealed class SingUpState {
    class Error(val error:String): SingUpState()
    object SuccessfulRegistration: SingUpState()
}