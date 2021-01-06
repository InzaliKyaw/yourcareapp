package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.ChatVO
import com.example.yourcare.R
import com.example.yourcare.util.SessionManager
import com.example.yourcare.view.viewholders.ChatPatientViewHolder

class ChatPatientTextAdapter : RecyclerView.Adapter<ChatPatientViewHolder>() {

    var doctorViewType = 1
    var patientViewType = 2
    var mData: MutableList<ChatVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPatientViewHolder {
        when (viewType) {
            patientViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_patient_text, parent, false)
                return ChatPatientViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_doctor_text, parent, false)
                return ChatPatientViewHolder(view)
            }
        }
    }


    override fun onBindViewHolder(holder: ChatPatientViewHolder, position: Int) {
        holder.bindChat(mData[position])
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
            mData[position].sendBy == SessionManager.patient_name.toString() -> {
                return patientViewType

            }
            else -> {
                return doctorViewType
            }
        }
    }

}