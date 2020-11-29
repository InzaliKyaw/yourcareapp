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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity:BaseActivity(),RegisterView{
    private lateinit var mPresenter:RegisterPresenter
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,RegisterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListeners()
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun showError(error: String) {

    }
    fun setUpPresenter(){
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    fun setUpActionListeners(){
        registerDoctor.setOnClickListener {
            mPresenter.onTapRegister(this,doctorEmail.text.toString(),doctorPswd.text.toString(),doctorName.text.toString(),doctorSpeciality.text.toString())
        }
    }
}