package com.example.yourcare.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.yourcare.R
import com.example.yourcare.activities.CaseSummaryActivity
import com.example.yourcare.mvp.MainPresenter
import com.example.yourcare.mvp.impls.MainPresenterImpl
import com.example.yourcare.view.view.DialogView
import kotlinx.android.synthetic.main.dialog_consultation_confirm.*

class ConfirmDialogFragment:DialogFragment(),DialogView{
    private lateinit var mPresener:MainPresenter
    private var patientId:Long = 0
    private lateinit var patientSpeciality:String

    companion object{
        const val SPECIALITY = "SPECIALITY"
        const val TAG_ADD_CONFIRM_DIALOG = "TAG_ADD_MEDICINE_DIALOG"
        const val PATIENT_ID = "ID"

        fun newFragment(speciality:String,patientId:Long)=
            ConfirmDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(SPECIALITY,speciality)
                    putLong(PATIENT_ID,patientId)
                }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_consultation_confirm,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        if (arguments != null){
            patientId= arguments?.getLong(PATIENT_ID)!!
            patientSpeciality = arguments?.getString(SPECIALITY).toString()
        }
        btnCancelConsultation.setOnClickListener {
            it.let {
                dismiss()
            }
        }
        btnConfirmConsultation.setOnClickListener {
            navigateToCaseSummaryScreen(patientId,patientSpeciality)
        }
    }
    fun setUpPresenter(){
        mPresener = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
    }

    override fun getAppContext(): Context {
        return this.requireContext()
    }
    fun navigateToCaseSummaryScreen(id: Long,speciality: String){
        startActivity(CaseSummaryActivity.newIntent(this.requireContext(),id,speciality))
    }

    override fun showError(error: String) {
    }
}