package com.example.chatapp.ui

data class ViewError(
    val message :String? = null,
    val t : Throwable? = null,
    val onTryAgainClickListener: OnTryAgainClickListener?= null
)
fun interface OnTryAgainClickListener{
    fun onTryAgainClicked()
}

