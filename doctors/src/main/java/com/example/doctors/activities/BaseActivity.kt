package com.example.doctors.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.view.BaseView

abstract class BaseActivity:AppCompatActivity(),BaseView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun getAppContext(): Context {
        return this
    }
    inline fun<reified T: AbstractBasePresenter<W>,reified  W:BaseView>getPresenter():T{
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W)presenter.initPresenter(this)
        return presenter
    }
}