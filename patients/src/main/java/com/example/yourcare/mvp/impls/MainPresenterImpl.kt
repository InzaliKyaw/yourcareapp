package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.MainPresenter
import com.example.yourcare.mvp.view.MainView

class MainPresenterImpl:MainPresenter,AbstractBasePresenter<MainView>() {
    private var mYourcareModel:YourcareModel = YourcareModelImpl
    val TAG = "Main Presenter"


    override fun loadAllSpeciality(lifecycleOwner: LifecycleOwner) {
        mYourcareModel.getAllSpeciality {
            Log.d(TAG, it)
        }.observe(lifecycleOwner, Observer {
            mView.showAllSpecialities(it)
        })
    }

    override fun loadAvailableDoctors(consultationReqId: Long) {
        mYourcareModel.consultationWithSameId(consultationReqId,onSuccess = {
            it.forEach {
                it.let {
                    mView.showAvailableDoctor(it.doctorsVO!!)
                }
            }
        },onFailure = {
            Log.d(TAG,"Failed Consultation" + it)
        })
    }


    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        loadAllSpeciality(lifecycleOwner)
    }

    override fun onTapCard(speciality: String) {
        mView.displayConfirmDialog(speciality)
    }
}