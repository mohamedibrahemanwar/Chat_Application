package com.example.chatapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.ui.ViewError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<ViewError>()
    val isLoading = MutableLiveData<Boolean>()

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()


    val firstNameError = MutableLiveData<String?>()
    val lastNameError = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val passwordConfirmationError = MutableLiveData<String?>()


    val auth = Firebase.auth
    fun register() {
        if (!validForm()) {
            return
        } else {
            isLoading.postValue(true)
            auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
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
        if (firstName.value.isNullOrBlank()) {
            // show error
            firstNameError.postValue("Please enter first name")
            isValid = false
        } else {
            firstNameError.postValue(null)
        }

        if (lastName.value.isNullOrBlank()) {
            // show error
            lastNameError.postValue("Please enter last name")
            isValid = false
        } else {
            lastNameError.postValue(null)
        }

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

        if (passwordConfirmation.value != password.value ||
            passwordConfirmation.value.isNullOrBlank()
        ) {
            // show error
            passwordConfirmationError.postValue("password doesn't match")
            isValid = false
        } else {
            passwordConfirmationError.postValue(null)
        }
        return isValid
    }
}