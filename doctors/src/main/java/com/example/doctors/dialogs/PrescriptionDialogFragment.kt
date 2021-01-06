package com.example.doctors.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.doctors.R
import com.example.doctors.activities.PrescriptionActivity
import com.example.doctors.mvp.presenters.PrescriptionPresenter
import com.example.doctors.mvp.presenters.impls.PresciptionPresenterImpl
import com.example.doctors.mvp.view.DialogView
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.Routine
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.dialog_add_medicine.*

class PrescriptionDialogFragment() : DialogFragment(), DialogView {

    private lateinit var mPresenter: PrescriptionPresenter
    var morning = false
    var noon = false
    var night = false
    private lateinit var rountineData:Routine

    companion object {
        const val TAG_ADD_MEDICINE_DIALOG = "TAG_ADD_MEDICINE_DIALOG"
        const val MEDICINE = "MEDICINE"
        const val PILL_NAME = "PILL_NAME"


        fun newFragment(medicineId: String,pillName:String): PrescriptionDialogFragment {
            val fragment = PrescriptionDialogFragment()
            val bundle = Bundle().apply {
                putString(MEDICINE, medicineId)
                putString(PILL_NAME,pillName)

            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_medicine, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        var medicineId = arguments?.getString(MEDICINE)
        var pillName = arguments?.getString(PILL_NAME)
        val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        medicineNameDialog.text = pillName

        (dayToDrinkTextField as? AutoCompleteTextView)?.setAdapter(adapter)


//        medicineId?.let {
//            medicineData = mPresenter.loadMedicineDataWithId(this, it, "bones_and_joints")
//        }

        rountineData = Routine(
            morning = false,
            noon = false,
            night = false,
            morningTimes = 0,
            noonTimes = 0,
            nightTimes = 0
        )

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = view.findViewById(checkedId)
            chip?.let {

                if (it == morningChip) {
                    // medicineData.routine?.morning = true
                    morning = true

                }
                if (it == noonChip) {
                    //medicineData.routine?.noon = true
                    noon = true
                }
                if (it == nightChip) {
                    //medicineData.routine?.night = true
                    night = true
                }
                rountineData = Routine(morning, noon, night)
            }
        }


        btnAddPrescription.setOnClickListener {

            mPresenter.onTapAddPrescription(
                this,
                1,
                medicineId.toString(),
                "",
                "",
                0,
                rountineData,
                3,
                medicineAmount.text.toString().toLong(),
                remarkText.text.toString()
            )
            startActivity(PrescriptionActivity.newIntent(this.requireContext(), "bones_and_joint"))
        }
    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(PresciptionPresenterImpl::class.java)
        }
    }


    override fun getAppContext(): Context {
        return this.requireContext()
    }

    override fun showError(error: String) {

    }
}