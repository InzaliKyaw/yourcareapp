package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.LoginPresenter
import com.example.yourcare.mvp.view.LoginView
import com.example.yourcare.util.savePatientToSession

class LoginPresenterImpl:LoginPresenter,AbstractBasePresenter<LoginView>() {
    val mAuthenticationModel:AuthenticationModel = AuthenticationModelImpl
    val mYourcareModel:YourcareModel = YourcareModelImpl

    val tag = "Login Presenter"
    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapLogin(lifecycleOwner: LifecycleOwner,email: String, password: String) {
        mAuthenticationModel.login(email,password,onSuccess = {
            Log.d(tag,"SUCCESS")
            mYourcareModel.getPatientsFromFirebaseApiAndSaveToDatabase(  {},{})

            mYourcareModel.getPatientByEmail(email).observe(lifecycleOwner, Observer {
                it?.let {
                    it.forEach {
                        //mView.navigateToHomeScreen(it.name.toString(), it.email.toString())
                        savePatientToSession(it)
                        mView.navigateToHomeScreen(it.id!!)
                    }
                }
            })

        },onFailure = {
            mView.showError(it)
            Log.d(tag,"FAILED")
        })


//        mAuthenticationModel.patientLogin(email,password,onSuccess = {
//            mView.navigateToHomeScreen()
//            Log.d(tag,"SUCCESS")
//        },onFailure = {
//            mView.showError(it)
//            Log.d(tag,"FAILED")
//
//        })
    }

}