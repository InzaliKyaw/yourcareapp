package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.RegisterFormPresenter
import com.example.doctors.mvp.view.RegisterFormView
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import kotlin.properties.Delegates

class RegisterFormPresenterImpl:RegisterFormPresenter,AbstractBasePresenter<RegisterFormView>() {

    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private var count = 0

    override fun onTapCreateAccount(
        lifecycleOwner: LifecycleOwner,
        deviceId:String,
        userName:String,
        phone:String,
        gender: String,
        speciality: String,
        experience: Long,
        degree: String,
        bio: String,
        address: String
    ) {
        mYourcareModel.getDoctors(onFailure = {
            mView?.showError(it)
        }).observe(lifecycleOwner, Observer {
            count = it.size
            var id = count + 1
         mYourcareModel.addDoctors( deviceId, id, userName,phone, speciality,gender,experience,degree,bio,address)
         mView?.navigateToHomeScreen(userName)
        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }


}