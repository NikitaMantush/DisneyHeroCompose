package com.mantushnikita.disneyherocompose.ui.singup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(

): ViewModel() {

    val state = MutableLiveData<SingUpState>()
    fun processAction(action: SingUpAction){
        when(action){
            is SingUpAction.OnSingUpClick->{
                login(action.email, action.registration)
            }
        }
    }
    private fun login(email: String, password:String){
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                state.value = SingUpState.SuccessfulRegistration
            }
            .addOnFailureListener{
                state.value = SingUpState.Error(it.localizedMessage)
            }
    }
}