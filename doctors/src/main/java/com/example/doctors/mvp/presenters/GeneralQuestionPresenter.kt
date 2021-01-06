package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.delegates.GeneralQuestionDelegates
import com.example.doctors.mvp.view.GeneralQuestionView

interface GeneralQuestionPresenter:BasePresenter<GeneralQuestionView>,GeneralQuestionDelegates {
    fun loadGQT(owner: LifecycleOwner)
}