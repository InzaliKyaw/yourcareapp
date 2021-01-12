package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.GeneralQuestionPresenter
import com.example.doctors.mvp.view.GeneralQuestionView
import com.example.doctors.util.SessionManager
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.SendByVO
import java.text.SimpleDateFormat
import java.util.*

class GeneralQuestionPresenterImpl:GeneralQuestionPresenter,AbstractBasePresenter<GeneralQuestionView>() {
    var mYourcareModel:YourcareModel = YourcareModelImpl


    override fun onTapQuestion(question: String) {
        var currentTime: Date = Calendar.getInstance().time
        var dateFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
        var sendAt: String = dateFormatter.format(currentTime).toString()
        var sendBy = SessionManager.doctor_name.toString()
        mYourcareModel.sendMessage(1,"","",question,sendAt,sendBy)
        mView.navigateToChat()
       // mView.sendSelectedQuestion(question)
    }

    override fun loadGQT(owner: LifecycleOwner) {
        mYourcareModel.getGeneralQuestionTemplate{
            mView.showError(it)
        }.observe(owner, Observer {
            it.let {
                mView.showGeneralQuestionTemplate(it)
            }

        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        loadGQT(owner)
    }
}