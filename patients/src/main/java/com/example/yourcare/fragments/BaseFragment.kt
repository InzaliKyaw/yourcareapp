package com.example.yourcare.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.yourcare.mvp.view.BaseView

abstract class BaseFragment:Fragment(),BaseView {
    override fun getAppContext(): Context {
        return this.requireContext()
    }

    override fun showError(error: String) {
    }
}