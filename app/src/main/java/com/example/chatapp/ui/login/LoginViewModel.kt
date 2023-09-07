package com.example.chatapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.ui.ViewError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<ViewError>()
    val isLoading = MutableLiveData<Boolean>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val emailError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()


    val auth = Firebase.auth
    fun login() {
        if (!validForm()) {
            return
        } else {
            isLoading.postValue(true)
            auth.signInWithEmailAndPassword(email.value!!, password.value!!)
                .addOnCompleteListener {
                    isLoading.postValue(false)
                    if (it.isSuccessful) {
                        errorLiveData.postValue(
                            ViewError(message = it.result.user?.uid)
                        )

                    } else {
                        // show Error
                        errorLiveData.postValue(
                            ViewError(it.exception?.localizedMessage)
                        )
                    }
                }
        }
    }

    private fun validForm(): Boolean {
        var isValid = true

        if (email.value.isNullOrBlank()) {
            // show error
            emailError.postValue("Please enter your email")
            isValid = false
        } else {
            emailError.postValue(null)
        }

        if (password.value.isNullOrBlank()) {
            // show error
            passwordError.postValue("Please enter a strong password")
            isValid = false
        } else {
            passwordError.postValue(null)
        }
        return isValid
    }
}