package com.mantushnikita.disneyherocompose.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    fun login(
        email: String, password: String, onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage)
            }
    }
}