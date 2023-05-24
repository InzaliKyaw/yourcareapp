package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.model.AuthenticationModel
import com.example.shared.data.model.AuthenticationModelImpl
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.PatientsVO
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.RegisterPresenter
import com.example.yourcare.mvp.view.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private var mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private var patientId:Long = 0

    companion object {
        const val TAG = "Register Patient"
    }

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapRegister(
        name: String,
        email: String,
        password: String,
        deviceId: String
    ) {

        mAuthenticationModel.patientRegister(email, password, name, deviceId, onSuccess = {
            mYourcareModel.getPatients(onSuccess = {
                var count = it.size
                patientId = (count +1).toLong()
                val patientVO = PatientsVO(
                    id = patientId,
                    name = name,
                    email = email,
                    deviceId = deviceId
                )
                mYourcareModel.registerNewPatient(patientVO)
                mView.successMessage()
                mView.navigateToLoginScreen(name, email)
            },{
                Log.d(TAG,"Failed Registration"+it)
            })

        }, onFailure = {
            Log.d(TAG,"Failed Registration"+it)
            mView.showError(it)
        })
    }
}