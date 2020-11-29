package com.example.doctors.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.view.BaseView

interface BasePresenter<V:BaseView> {
    fun onUiReady(context: Context,owner:LifecycleOwner)
    fun initPresenter(view:V)
}