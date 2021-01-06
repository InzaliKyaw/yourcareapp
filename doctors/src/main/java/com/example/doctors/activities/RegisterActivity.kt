package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.doctors.R
import com.example.doctors.mvp.presenters.RegisterPresenter
import com.example.doctors.mvp.presenters.impls.LoginPresenterImpl
import com.example.doctors.mvp.presenters.impls.RegisterPresenterImpl
import com.example.doctors.mvp.view.LoginView
import com.example.doctors.mvp.view.RegisterView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context,RegisterActivity::class.java)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListeners()
    }

//    override fun navigateToCreateAccountScreen() {
//
//    }

    override fun navigateToCreateAccountScreen(userName: String) {
        startActivity(RegisterFormActivity.newIntent(this,userName))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    fun setUpActionListeners() {
        registerDoctor.setOnClickListener {
            mPresenter.onTapRegister(this, doctorEmail.text.toString(), doctorPswd.text.toString(), doctorName.text.toString(),this)
        }
        loginTxtBtn.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
        }
    }
}