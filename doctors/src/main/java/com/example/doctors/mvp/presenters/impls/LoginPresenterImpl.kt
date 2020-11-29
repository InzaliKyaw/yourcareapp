package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.LoginPresenter
import com.example.doctors.mvp.view.LoginView
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {
    private var mAuthenticationModel:AuthenticationModel = AuthenticationModelImpl
    override fun onTapLogin(context: Context, email: String, password: String) {
        mAuthenticationModel.login(email,password,onSuccess = {
            mView.navigateToHomeScreen()
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