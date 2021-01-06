package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientsVO
import com.example.yourcare.R
import com.example.yourcare.adapters.ConfirmCaseSummaryAdapter
import com.example.yourcare.mvp.ConfirmCaseSummaryPresenter
import com.example.yourcare.mvp.impls.ConfirmPatientCaseSummaryImpl
import com.example.yourcare.mvp.view.ConfirmCaseSummaryView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_confirm_consultation.*

class ConfirmCaseSummaryActivity:BaseActivity(),ConfirmCaseSummaryView {

    private lateinit var mPresenter:ConfirmCaseSummaryPresenter
    private var patientId:Long = 0
    private lateinit var mAdapter:ConfirmCaseSummaryAdapter
    private lateinit var speciality: String

    companion object{
        const val PATIENT_ID = "ID"
        const val SPECIALITY = "SPECIALITY"
        fun newIntent(context: Context,patientId:Long,speciality:String):Intent{
            val intent = Intent(context,ConfirmCaseSummaryActivity::class.java)
            intent.putExtra(PATIENT_ID,patientId)
            intent.putExtra(SPECIALITY,speciality)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_consultation)
        setUpPresenter()

        patientId = intent.getLongExtra(PATIENT_ID,0)
        speciality = intent.getStringExtra(SPECIALITY).toString()

        mPresenter.loadPatientGeneralData(this,patientId)
        mPresenter.loadPatientCaseSummary(this,patientId)
        mAdapter = ConfirmCaseSummaryAdapter()
        setUpConfirmRecycler(mAdapter)
        setUpListeners()

        btnStartConsultationRequest.setOnClickListener {
            mPresenter.startConsultationRequest(this,patientId,speciality)
        }

    }
    fun setUpPresenter() {
        mPresenter = getPresenter<ConfirmPatientCaseSummaryImpl, ConfirmCaseSummaryView>()
    }

    fun setUpListeners(){
        setSupportActionBar(confirmToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setUpConfirmRecycler(confirmAdapter:ConfirmCaseSummaryAdapter){
        rvRelatedQConfirm.adapter = confirmAdapter
        val linearLayout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvRelatedQConfirm.layoutManager = linearLayout
    }
    override fun navigateToStartConsultation(patientId: Long,consultationRequestId:Long) {
        startActivity(MainActivity.newIntent(this,patientId,consultationRequestId))
    }

    override fun showCaseSummary(caseSummaryList: List<CaseSummaryVO>) {
        mAdapter.setNewData(caseSummaryList.toMutableList())
    }

    override fun showPatientData(patientsVO: PatientsVO) {
        patientNameCfm.text = patientsVO.name
        patientHeightCfm.text = patientsVO.height
        patientBirthdateCfm.text = patientsVO.birthdate
        bloodTypeCfm.text = patientsVO.bloodType
        weightCfm.text = patientsVO.weight
        bloodPressureCfm.text = patientsVO.bloodPressure
        allergicPillCfm.text = patientsVO.allergyMedicine

    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}