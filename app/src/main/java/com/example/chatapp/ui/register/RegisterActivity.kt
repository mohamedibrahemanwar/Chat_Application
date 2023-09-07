package com.example.chatapp.ui.register

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.example.chatapp.ui.showMessage

class RegisterActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.errorLiveData.observe(this){
            showMessage(it.message?:"something went error",
                posActionName = "ok",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                )
        }
    }

    private fun initViews() {
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

    }
}