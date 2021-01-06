package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO
import com.example.yourcare.R
import com.example.yourcare.adapters.CaseSummaryAdapter
import com.example.yourcare.mvp.CaseSummaryPresenter
import com.example.yourcare.mvp.CaseSummaryQuestionPresenter
import com.example.yourcare.mvp.impls.CaseSummaryPresenterImpl
import com.example.yourcare.mvp.impls.CaseSummaryQuestionPresenterImpl
import com.example.yourcare.mvp.view.CaseSummaryQuestionView
import com.example.yourcare.mvp.view.CaseSummaryView
import kotlinx.android.synthetic.main.activity_casesummary.*
import kotlinx.android.synthetic.main.activity_casesummary.stateProgressBar
import kotlinx.android.synthetic.main.activity_casesummary_two.*
import kotlinx.android.synthetic.main.item_specialities.*

class CaseSummaryTwoActivity:BaseActivity(),CaseSummaryQuestionView{
    private lateinit var mPresenter: CaseSummaryQuestionPresenter
    private lateinit var mAdapter: CaseSummaryAdapter
    private var patientId: Long = 0
    private lateinit var patientSpeciality:String

    companion object{
        const val PATIENT_ID = "ID"
        const val SPECIALITY = "SPECIALITY"
        fun newIntent(context: Context,patientId:Long,speciality:String): Intent {
            val intent = Intent(context,CaseSummaryTwoActivity::class.java)
            intent.putExtra(PATIENT_ID,patientId)
            intent.putExtra(SPECIALITY,speciality)
            return intent

        }
    }
    private var caseSummaryVoList : ArrayList<CaseSummaryVO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casesummary_two)
        setUpPresenter()
        mAdapter = CaseSummaryAdapter(mPresenter)
        setUpRecycler(mAdapter)

        patientId = intent.extras?.getLong(PATIENT_ID) ?: 0
        patientSpeciality = intent?.getStringExtra(SPECIALITY).toString()


        setSupportActionBar(caseSummaryToolbarTwo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        mPresenter.loadSpecialQuestions(this,patientSpeciality)

        var stateLable = arrayOf("အထွေထွေမေးခွန်းများ","ရောဂါဆိုင်ရာမေးခွန်းများ")
        stateProgressBar.setStateDescriptionData(stateLable)
        btnConsultation.setOnClickListener {
           // mPresenter.onTapConfirmConsultation()
            caseSummaryVoList.forEach {
                mPresenter.onTapConfirmConsultation(patientId,it.id!!,it.question,it.answer,patientSpeciality)
            }
        }
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<CaseSummaryQuestionPresenterImpl, CaseSummaryQuestionView>()
    }
    fun setUpRecycler(mAdapter:CaseSummaryAdapter){
        rvRelatedQuestions.adapter = mAdapter
        val linearLayout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvRelatedQuestions.layoutManager = linearLayout
    }

    override fun replaceAnswerList(position: Int, caseSummaryVO: CaseSummaryVO) {
        caseSummaryVoList[position] = caseSummaryVO
        Log.d("MMM",caseSummaryVO.answer.toString())
    }

    override fun showSpecialQuestion(specialQuestion: List<RelatedQuestionVO>) {
        mAdapter.setNewData(specialQuestion.toMutableList())
        for(item in specialQuestion){
            caseSummaryVoList.add(CaseSummaryVO(item.id,item.question,""))
        }
        mAdapter.setCaseSummaryList(caseSummaryList = caseSummaryVoList)

    }

    override fun navigateToConfirmRequestScreen(patientId: Long,speciality: String) {
        startActivity(ConfirmCaseSummaryActivity.newIntent(this,patientId,speciality))
    }

    override fun showError(error: String) {
    }
}