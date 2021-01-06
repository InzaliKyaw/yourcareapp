package com.example.yourcare.mvp

import androidx.lifecycle.LifecycleOwner
import com.example.yourcare.delegates.SpecialitiesDelegates
import com.example.yourcare.mvp.view.MainView

interface MainPresenter:BasePresenter<MainView>, SpecialitiesDelegates {
    fun loadAllSpeciality(lifecycleOwner: LifecycleOwner)
    fun loadAvailableDoctors(consultationReqId:Long)
}