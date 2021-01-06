package com.example.doctors.view.viewholders

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.item_casesummary_qa.view.*

class ConsultationRequestCaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bindCaseQA(caseSummaryVO: CaseSummaryVO){
        itemView.question.text = caseSummaryVO.question
        itemView.answer.text = caseSummaryVO.answer
    }
}