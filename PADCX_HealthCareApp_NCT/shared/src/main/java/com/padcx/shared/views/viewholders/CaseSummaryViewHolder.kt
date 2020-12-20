package com.padcx.shared.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.questions_items.view.*

class CaseSummaryViewHolder(itemView:View):
    BaseCaseSummaryViewHolder(itemView) {
    override fun bindData(data: QuestionVO) {
        itemView.tvQuestion.text = data.sentence
        itemView.tvSpecialityAnswer.text = data.answer
        mData = data
    }
    override fun bindCount(count:Int) {
        itemView.tvCount.text = "$count ). "
    }
}