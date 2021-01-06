package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.doctors.R
import com.example.doctors.mvp.presenters.LoginPresenter
import com.example.doctors.mvp.presenters.impls.LoginPresenterImpl
import com.example.doctors.mvp.view.LoginView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:BaseActivity(),LoginView{
    private lateinit var mPresenter: LoginPresenter
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context ,LoginActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()
        setUpActionListeners()
    }

    override fun navigateToHomeScreen(email:String) {
        startActivity(MainActivity.newIntent(this,email))
    }
    fun setUpPresenter(){
        mPresenter = getPresenter<LoginPresenterImpl,LoginView>()
    }

    fun setUpActionListeners(){
        btnLoginDoc.setOnClickListener {
            mPresenter.onTapLogin(this,doctorLoginEmail.text.toString(),doctorLoginPswd.text.toString())
        }
        btnRegisterDoc.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }


    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }


    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG)
    }
}