package com.example.yourcare.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.view.BaseView

abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun getAppContext():Context{
        return this
    }
    inline fun<reified T: AbstractBasePresenter<W>,reified  W: BaseView>getPresenter():T{
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W)presenter.initPresenter(this)
        return presenter
    }

}