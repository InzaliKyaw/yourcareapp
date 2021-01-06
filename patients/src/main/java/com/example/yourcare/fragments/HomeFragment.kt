package com.example.yourcare.fragments

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.DoctorsVO
import com.example.shared.data.vos.SpecialitiesVO
import com.example.yourcare.R
import com.example.yourcare.activities.ChatActivity
import com.example.yourcare.activities.MainActivity
import com.example.yourcare.adapters.SpecialitiesAdapter
import com.example.yourcare.dialog.ConfirmDialogFragment
import com.example.yourcare.mvp.MainPresenter
import com.example.yourcare.mvp.impls.MainPresenterImpl
import com.example.yourcare.mvp.view.MainView
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.zip.Inflater

class HomeFragment : BaseFragment(), MainView {

    private lateinit var mSpecialitiesAdapter: SpecialitiesAdapter
    private lateinit var mPresenter: MainPresenter
    private var patientId: Long = 0
    private var consultationRequestId:Long = 0
    var TAG = "HOMEFRAGMENT"

    companion object {
        const val PATIENT_ID = "ID"
        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newInstance(id: Long,consultationRequestId:Long) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putLong(PATIENT_ID, id)
                    putLong(CONSULTATION_ID,consultationRequestId)
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        mPresenter.onUiReady(this.requireContext(), this)
        mSpecialitiesAdapter = SpecialitiesAdapter(mPresenter)
        mPresenter.loadAvailableDoctors(1)
        setUpSpecialityRecyclerView(mSpecialitiesAdapter)
        if (arguments != null){
            patientId = arguments?.getLong(PATIENT_ID)!!
            consultationRequestId = arguments?.getLong(CONSULTATION_ID)!!
            Log.d(TAG, "ID" + patientId)
            Log.d(TAG,"CONSULTATION_ID"+consultationRequestId)
        }

    }

    fun setUpSpecialityRecyclerView(specialitiesAdapter: SpecialitiesAdapter) {
        rvSpecialities.adapter = specialitiesAdapter
        var linearLayout =
            GridLayoutManager(this.requireContext(), 2, GridLayoutManager.VERTICAL, false)
        rvSpecialities.layoutManager = linearLayout
    }

    fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun showAllSpecialities(specialities: List<SpecialitiesVO>) {
        mSpecialitiesAdapter.setNewData(specialities.toMutableList())
    }

    override fun displayConfirmDialog(speciality: String) {
        fragmentManager?.let {
            ConfirmDialogFragment.newFragment(speciality, patientId)
                .show(it, ConfirmDialogFragment.TAG_ADD_CONFIRM_DIALOG)
        }
    }

    override fun showAvailableDoctor(doctorsVO: DoctorsVO) {
        acceptedDoctorName.text = doctorsVO.name.toString()
        acceptedDoctorNameTxt.text = doctorsVO.name.toString()
        acceptedSpeciality.text = doctorsVO.speciality.toString()
        doctorBio.text = doctorsVO.bio.toString()
        btnStartConsultation.setOnClickListener {
            startActivity(ChatActivity.newIntent(this.requireContext(),patientId))
        }

    }
}