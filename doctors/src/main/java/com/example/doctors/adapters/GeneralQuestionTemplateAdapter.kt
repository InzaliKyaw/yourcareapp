package com.example.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.delegates.GeneralQuestionDelegates
import com.example.doctors.view.viewholders.GeneralQuestionTemplateViewHolder
import com.example.shared.data.vos.GeneralQuestionTemplateVO

class GeneralQuestionTemplateAdapter(questionDelegates: GeneralQuestionDelegates):RecyclerView.Adapter<GeneralQuestionTemplateViewHolder>() {
    var mData:MutableList<GeneralQuestionTemplateVO> = arrayListOf()
    var mDelegates:GeneralQuestionDelegates = questionDelegates
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralQuestionTemplateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_question,parent,false)
        return GeneralQuestionTemplateViewHolder(view,mDelegates)
    }

    override fun onBindViewHolder(holder: GeneralQuestionTemplateViewHolder, position: Int) {
        holder.bindQuestion(mData[position])
    }
    fun setNewData(data:MutableList<GeneralQuestionTemplateVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}