package com.example.doctors.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.LoginPresenter
import com.example.doctors.mvp.view.LoginView
import com.example.doctors.util.saveDoctorToSession
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {

    private var mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private var mYourcareModel:YourcareModel = YourcareModelImpl
    private lateinit var userName:String
    private val TAG = "LOGIN PRESENTER"
    override fun onTapLogin(lifecycleOwner: LifecycleOwner, email: String, password: String) {
        mAuthenticationModel.login(email,password,onSuccess = {
            mYourcareModel.getDoctors {
                Log.d(TAG,"FAILED :"+ it)
            }.observe(lifecycleOwner, Observer {
                Log.d(TAG,"Doctors"+it)
            })
            mYourcareModel.getDoctorByEmail(email).observe(lifecycleOwner, Observer {
                it.let {
                    it.forEach {
                        saveDoctorToSession(it)
                        mView.navigateToHomeScreen(email)
                    }
                }

            })

        },onFailure ={
            mView.showError(it)
        } )
    }

    override fun onTapRegister() {
       mView.navigateToRegisterScreen()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }
}