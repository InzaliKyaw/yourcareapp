package com.example.yourcare.view.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO
import com.example.yourcare.delegates.SpecialQuestionDelegates
import com.example.yourcare.delegates.SpecialitiesDelegates
import com.example.yourcare.mvp.view.CaseSummaryView
import kotlinx.android.synthetic.main.item_special_question.view.*

class CaseSummaryViewHolder(itemView:View,var mCaseSummaryList:List<CaseSummaryVO>,var mDelegates: SpecialQuestionDelegates):BaseViewHolder<RelatedQuestionVO>(itemView) {
    override fun bindData(data: RelatedQuestionVO) {
        mData = data
        itemView.questionS.text ="(${adapterPosition+1}) ${data.question}"

        mCaseSummaryList.let {
            itemView.answerS.text = Editable.Factory.getInstance().newEditable(mCaseSummaryList[adapterPosition].answer)
        }
        itemView.questionS.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var caseSummaryVO = CaseSummaryVO(data.id,data.question,itemView.answerS.text.toString())
                mDelegates.onAnswerChange(adapterPosition,caseSummaryVO)
            }

            override fun afterTextChanged(s: Editable?) {
                var caseSummaryVO = CaseSummaryVO(data.id,data.question,itemView.questionS.text.toString())
                mDelegates.onAnswerChange(adapterPosition,caseSummaryVO)
            }
        })
    }
}