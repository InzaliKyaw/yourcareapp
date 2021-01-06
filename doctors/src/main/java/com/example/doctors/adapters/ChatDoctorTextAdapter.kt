package com.example.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.util.SessionManager
import com.example.doctors.view.viewholders.ChatDoctorTextViewHolder
import com.example.shared.data.vos.ChatVO

class ChatDoctorTextAdapter : RecyclerView.Adapter<ChatDoctorTextViewHolder>() {

    var doctorViewType = 1
    var patientViewType = 2
    var mData: MutableList<ChatVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatDoctorTextViewHolder {
        when (viewType) {
            doctorViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_doctor_text, parent, false)
                return ChatDoctorTextViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_patient_text, parent, false)
                return ChatDoctorTextViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ChatDoctorTextViewHolder, position: Int) {
        holder.bindChatText(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setNewData(data: MutableList<ChatVO>) {
        mData = data
        mData.sortBy {
            it.sendAt
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        when {
            mData[position].sendBy == SessionManager.doctor_name.toString() -> {
                return doctorViewType
            }
            else -> {
                return patientViewType
            }
        }
    }
}