package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.adapters.GeneralQuestionTemplateAdapter
import com.example.doctors.mvp.presenters.GeneralQuestionPresenter
import com.example.doctors.mvp.presenters.impls.GeneralQuestionPresenterImpl
import com.example.doctors.mvp.presenters.impls.LoginPresenterImpl
import com.example.doctors.mvp.view.GeneralQuestionView
import com.example.doctors.mvp.view.LoginView
import com.example.shared.data.vos.GeneralQuestionTemplateVO
import kotlinx.android.synthetic.main.activity_gqtemplate.*

class GeneralQuestionTemplateActivity:BaseActivity(),GeneralQuestionView {
    private lateinit var mPresenter:GeneralQuestionPresenter
    private lateinit var generalQuestionTemplateAdapter:GeneralQuestionTemplateAdapter
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,GeneralQuestionTemplateActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gqtemplate)
        setUpPresenter()
        setUpListeners()

        mPresenter.onUiReady(this,this)
        generalQuestionTemplateAdapter = GeneralQuestionTemplateAdapter(mPresenter)
        setUpGeneralRecycler(generalQuestionTemplateAdapter)
    }

    override fun showGeneralQuestionTemplate(gQTlist: List<GeneralQuestionTemplateVO>) {
        generalQuestionTemplateAdapter.setNewData(gQTlist.toMutableList())
    }

    override fun sendSelectedQuestion(quesion: String) {

        startActivity(ChatActivity.newIntent(this,1))
    }

    override fun navigateToChat() {
        startActivity(ChatActivity.newIntent(this,1))
    }

    override fun showError(error: String) {

    }

    fun setUpListeners(){
        setSupportActionBar(generalQTtoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    fun setUpPresenter(){
        mPresenter = getPresenter<GeneralQuestionPresenterImpl, GeneralQuestionView>()
    }
    fun setUpGeneralRecycler(generalQuestionTemplateAdapter: GeneralQuestionTemplateAdapter){
        rvGQT.adapter = generalQuestionTemplateAdapter
        val linearLayout = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rvGQT.layoutManager = linearLayout
    }
}