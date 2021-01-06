package com.example.yourcare.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.example.yourcare.R
import com.example.yourcare.activities.BaseActivity
import com.example.yourcare.mvp.RegisterPresenter
import com.example.yourcare.mvp.impls.RegisterPresenterImpl
import com.example.yourcare.mvp.view.RegisterView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(),RegisterView {
    private lateinit var mPresenter: RegisterPresenter

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,RegisterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListener()
    }

    fun setUpPresenter(){
        mPresenter = getPresenter<RegisterPresenterImpl,RegisterView>()
    }
    @SuppressLint("HardwareIds")
    fun setUpActionListener(){
        var deviceId:String = Settings.Secure.getString(contentResolver,Settings.Secure.ANDROID_ID)
        registerPatient.setOnClickListener {
            mPresenter.onTapRegister(patientName.text.toString(),patientEmail.text.toString(),patientPswd.text.toString(),deviceId)
        }
        navigateLoginTxt.setOnClickListener {
            navigateToLoginScreen(patientName.text.toString(),patientEmail.text.toString())
        }
    }

    override fun navigateToLoginScreen(name:String,email:String) {
        startActivity(LoginActivity.newIntent(this,name, email))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG)
    }


}