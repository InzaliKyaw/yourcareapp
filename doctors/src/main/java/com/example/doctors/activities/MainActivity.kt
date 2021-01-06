package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.adapters.ConsultationRequestAdapter
import com.example.doctors.mvp.presenters.MainPresenter
import com.example.doctors.mvp.presenters.impls.MainPresenterImpl
import com.example.doctors.mvp.view.MainView
import com.example.doctors.util.SessionManager
import com.example.shared.data.vos.ConsultationRequestVO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    private lateinit var mPresenter: MainPresenter
    private lateinit var consultationRequestAdapter: ConsultationRequestAdapter

    companion object {
        const val EMAIL = "EMAIL"
        fun newIntent(context: Context,email:String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EMAIL,email)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPresenter()
        mPresenter.onUiReady(this, this)
        currentDocName.text = SessionManager.doctor_name.toString()
        mPresenter.loadConsultationRequestWithSpeciality("Bone and  Joints")
//        var doctorName = intent.extras?.get("MAIN")
//        currentDocName.text = doctorName.toString()
        consultationRequestAdapter = ConsultationRequestAdapter(mPresenter)
        setUpConsultationRecyclerView(consultationRequestAdapter)

//        callDoctors()
    }

    /*
        override fun callDoctors() {
            mPresenter.onUiReady(this,this)
            mPresenter.loadDoctorsWithSpecialities("Stomach")
            mPresenter.loadGeneralQuestionTemplate("one time")
            mPresenter.loadMostUsedMedicineBySpeciality("mental_health")
            //mPresenter.checkout(caseSummaryVO = getCaseSummary(),medicineVO = getMedicine(), patientsVO = getPatient(),3,"1:20 pm","Yan Pyay 5")
        }

     */
    fun setUpConsultationRecyclerView(consultationRequestAdapter: ConsultationRequestAdapter) {
        rvConsultationRequest.adapter = consultationRequestAdapter
        val linearLayoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rvConsultationRequest.layoutManager = linearLayoutManager
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    override fun showConsultationRequest(consultationRequestList: List<ConsultationRequestVO>) {
        consultationRequestAdapter.setNewData(consultationRequestList.toMutableList())
    }

    override fun navigateToStartConsultationScreen(consultationId: Long) {
      startActivity(StartConsultationActivity.newIntent(this,consultationId))
    }

    override fun showError(error: String) {
    }
}