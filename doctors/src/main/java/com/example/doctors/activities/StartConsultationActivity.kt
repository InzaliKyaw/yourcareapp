package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.adapters.ConsultationRequestCaseSAdapter
import com.example.doctors.mvp.presenters.StartConsultationPresenter
import com.example.doctors.mvp.presenters.impls.StartConsultationPresenterImpl
import com.example.doctors.mvp.view.StartConsultationView
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import kotlinx.android.synthetic.main.activity_start_consultation.*

class StartConsultationActivity:BaseActivity(),StartConsultationView{

    private lateinit var mPresenter:StartConsultationPresenter
    private var consultationId:Long = 0
    private lateinit var mConsultationRequestCaseSAdapter:ConsultationRequestCaseSAdapter
    private lateinit var speciality:String

    companion object{
        const val CONSULTATION = "CONSULTATION"
        const val TAG = "START CONSULTATION"
        fun newIntent(context: Context,consultationId:Long): Intent {
            val intent = Intent(context,StartConsultationActivity::class.java)
            intent.putExtra(CONSULTATION,consultationId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_consultation)
        setUpPresenter()
        setSupportActionBar(startConsultationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
         consultationId = intent.getLongExtra(CONSULTATION,0)
        //mPresenter.loadConsultationRequestWithSpeciality("Bones and Joints")
        mPresenter.loadConsultationRequestWithId(this,consultationId)
        mPresenter.loadCaseSummaryQAndA(consultationId)
        mConsultationRequestCaseSAdapter = ConsultationRequestCaseSAdapter()
        setUpRecycelQA(mConsultationRequestCaseSAdapter)
        startConsultationBtn.setOnClickListener {
            mPresenter.onTapStartConsultation()
        }

    }
    fun setUpPresenter() {
        mPresenter = getPresenter<StartConsultationPresenterImpl, StartConsultationView>()
    }

    override fun navigateToChatScreen() {
        startActivity(ChatActivity.newIntent(this, consultationId))
    }

    override fun showConsultationRequestData(consultationRequestVO: ConsultationRequestVO) {
        patientNameSC.text = consultationRequestVO.patientsVO?.name.toString()
        patientBirthdaySC.text = consultationRequestVO.patientsVO?.birthdate.toString()
        patientHeightSC.text = consultationRequestVO.patientsVO?.height.toString()
        patientBloodSC.text = consultationRequestVO.patientsVO?.bloodType.toString()
        patientAllergySC.text = consultationRequestVO.patientsVO?.allergyMedicine.toString()
        patientWeightSC.text = consultationRequestVO.patientsVO?.weight.toString()
        patientBloodPressureSC.text = consultationRequestVO.patientsVO?.bloodPressure.toString()
        speciality = consultationRequestVO.specialitiesVO?.name.toString()
        Log.d(TAG,"SPECIALITY"+speciality)
    }

    fun setUpRecycelQA(mAdapter:ConsultationRequestCaseSAdapter){
        rvCaseSummaryQA.adapter = mAdapter
        val linearLayoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rvCaseSummaryQA.layoutManager = linearLayoutManager

    }

    override fun showCaseSummaryQAndA(caseSummaryList: List<CaseSummaryVO>) {
        mConsultationRequestCaseSAdapter.setNewData(caseSummaryList.toMutableList())
    }

    override fun showError(error: String) {

    }
}

