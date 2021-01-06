package com.example.doctors.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.GeneralQuestionPresenter
import com.example.doctors.mvp.view.GeneralQuestionView
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.SendByVO

class GeneralQuestionPresenterImpl:GeneralQuestionPresenter,AbstractBasePresenter<GeneralQuestionView>() {
    var mYourcareModel:YourcareModel = YourcareModelImpl


    override fun onTapQuestion(question: String) {
        //NeedToFixWithGetCurrentUser
        var sendBy = "Aung Aung"
        mYourcareModel.sendMessage(1,"","",question,"",sendBy)
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