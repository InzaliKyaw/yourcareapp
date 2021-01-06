package com.example.doctors.view.viewholders

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.delegates.GeneralQuestionDelegates
import com.example.shared.data.vos.GeneralQuestionTemplateVO
import kotlinx.android.synthetic.main.item_general_question.view.*

class GeneralQuestionTemplateViewHolder(itemView: View,private val delegates: GeneralQuestionDelegates):BaseGeneralQuestionViewholder(itemView) {
    init {
        itemView.generalQLnl.setOnClickListener {
            mData?.let {
                delegates.onTapQuestion(it.question)
            }
        }
    }
    fun bindQuestion(generalQuestionTemplateVO: GeneralQuestionTemplateVO){
        mData = generalQuestionTemplateVO
        itemView.generalQText.text = generalQuestionTemplateVO.question
    }
}