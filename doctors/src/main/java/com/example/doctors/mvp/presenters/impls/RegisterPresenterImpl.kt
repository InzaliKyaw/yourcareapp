package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.RegisterPresenter
import com.example.doctors.mvp.view.RegisterView
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private var mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private val tag = "RegisterPresenter"

    override fun onTapRegister(
        context: Context,
        email: String,
        password: String,
        userName: String,
        lifecycleOwner: LifecycleOwner
    ) {

       mAuthenticationModel.register(email, password, userName, onSuccess = {
           //mView.navigateToLoginScreen()
           mView.navigateToCreateAccountScreen(userName)
       }, onFailure = {
           mView.showError(it)
       })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}