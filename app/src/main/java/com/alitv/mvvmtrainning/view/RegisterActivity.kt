package com.alitv.mvvmtrainning.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alitv.mvvmtrainning.R
import com.alitv.mvvmtrainning.viewmodel.LoginViewModel
import com.alitv.mvvmtrainning.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel:RegisterViewModel by lazy{
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener{
            registerViewModel.register(
                et_email.text.toString(),
                et_name.text.toString(),
                et_address.text.toString(),
                et_dob.text.toString(),
                et_password.text.toString()
            )
        }
        setObserver()
    }

    private fun setObserver() {
        registerViewModel.getRegisterResponseModel().observe(this, Observer{
            if (it != null){
                Toast.makeText(this, "User Name "+it.userModel.userName, Toast.LENGTH_SHORT).show()
            }
        })

        registerViewModel.getErrorListener().observe(this, Observer {
            if (it){
                Toast.makeText(this, "Register failed !!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}