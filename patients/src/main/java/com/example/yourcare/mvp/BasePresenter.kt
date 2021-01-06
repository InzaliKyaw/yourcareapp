package com.example.yourcare.mvp

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.yourcare.mvp.view.BaseView

interface BasePresenter<v:BaseView> {
    fun onUiReady(context: Context,lifecycleOwner: LifecycleOwner)
    fun initPresenter(view:v)
}