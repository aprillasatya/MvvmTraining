package com.alitv.mvvmtrainning.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alitv.mvvmtrainning.R
import com.alitv.mvvmtrainning.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    //private EditTest et_email;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // findViewById(R.id.et_email);

        //et_email.getText().toString();
        et_email.text.toString()

        btn_login.setOnClickListener{
            loginViewModel.login(et_email.text.toString(), et_password.text.toString())
        }

        tv_create_new_account.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
//        Log.i("TAG", "Password = "+login("asd"))
        setObserver()
    }

    private fun setObserver() {
        loginViewModel.getLoginResponseModel().observe(this, Observer{
            if(it != null){
                Toast.makeText(this, "User Id "+it.userModel.userId, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        })

        loginViewModel.getErrorListener().observe(this, Observer{
            if(it){
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}