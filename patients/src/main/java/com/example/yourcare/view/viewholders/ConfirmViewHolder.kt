package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.item_show_case_summary.view.*

class ConfirmViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bindList(caseSummaryVO: CaseSummaryVO){
        itemView.questionCase.text = caseSummaryVO.question
        itemView.answerCase.text = caseSummaryVO.answer
    }
}