package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.RegisterPresenter
import com.example.doctors.mvp.view.RegisterView
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import kotlin.properties.Delegates

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private var mAuthenticationModel:AuthenticationModel = AuthenticationModelImpl
    private var mYourcareModel:YourcareModel = YourcareModelImpl

    var count:Int = 0
    override fun onTapRegister(
        context: Context,
        email: String,
        password: String,
        userName: String,
        speciality: String
    ) {

       mAuthenticationModel.register(email,password,userName,speciality,onSuccess = {
           mView.navigateToLoginScreen()

            mYourcareModel.getDoctorCount(onSuccess = {
                count = it
           },onFailure = {

            })
           var id = count+1
           mYourcareModel.addDoctors(id,userName,speciality)
       },onFailure = {
           mView.showError(it)
       })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}