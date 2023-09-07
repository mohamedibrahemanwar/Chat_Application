package com.example.chatapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.example.chatapp.ui.register.RegisterViewModel
import com.example.chatapp.ui.showMessage

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.errorLiveData.observe(this) {
            showMessage(it.message ?: "something went error",
                posActionName = "ok",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            )
        }
    }

    private fun initViews() {
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

    }
}