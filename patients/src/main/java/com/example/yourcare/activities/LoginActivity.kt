package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.yourcare.R
import com.example.yourcare.mvp.LoginPresenter
import com.example.yourcare.mvp.impls.LoginPresenterImpl
import com.example.yourcare.mvp.view.LoginView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity:BaseActivity(),LoginView{
    private lateinit var mPresenter:LoginPresenter
    private lateinit var patientName:String
    private lateinit var patientEmail:String

    companion object{
        const val PATIENT = "PATIENT"
        fun newIntent(context: Context,name:String,email:String):Intent{
            val intent = Intent(context,LoginActivity::class.java)
            intent.putExtra(PATIENT,name)
            intent.putExtra(PATIENT,email)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpPresenter()
        setUpActionListener()

    }

    fun setUpPresenter(){
        mPresenter = getPresenter<LoginPresenterImpl,LoginView>()
    }
    fun setUpActionListener(){

        btnLoginPatient.setOnClickListener {
            mPresenter.onTapLogin(this,patientLoginEmail.text.toString(),patientLoginPswd.text.toString())

        }
        btnRegisterPatient.setOnClickListener {
            navigateToRegisterView()
        }

    }

    override fun navigateToRegisterView() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun navigateToHomeScreen(id:Long) {
        startActivity(MainActivity.newIntent(this,id,0))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}