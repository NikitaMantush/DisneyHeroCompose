package com.mantushnikita.disneyherocompose.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mantushnikita.disneyherocompose.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    val state = MutableLiveData<LoginState>()
    fun processAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> {
                login(action.email, action.registration)
            }
        }
    }

    private fun login(email: String, password: String) {
        repository.login(email, password, {
            state.value = LoginState.SuccessfulLogin
        }, {
            state.value = LoginState.Error(it)
        })
    }
}