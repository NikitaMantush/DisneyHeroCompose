package com.mantushnikita.disneyherocompose.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    val state = MutableLiveData<LoginState>()
    fun processAction(action: LoginAction){
        when(action){
            is LoginAction.OnLoginClick->{
                login(action.email, action.registration)
            }
        }
    }
    private fun login(email: String, password:String){
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
            state.value = LoginState.SuccessfulLogin
        }
            .addOnFailureListener{
            state.value = LoginState.Error(it.localizedMessage)
        }
    }
}