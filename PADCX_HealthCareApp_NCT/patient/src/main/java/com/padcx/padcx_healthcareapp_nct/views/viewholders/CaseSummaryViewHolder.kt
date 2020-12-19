package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.patient_speciality_question_items.view.*
import kotlinx.android.synthetic.main.speciality_question_items.view.*
import kotlinx.android.synthetic.main.speciality_question_items.view.tvCount
import kotlinx.android.synthetic.main.speciality_question_items.view.tvQuestion

class CaseSummaryViewHolder(itemView:View):BaseCaseSummaryViewHolder(itemView) {
    override fun bindData(data: QuestionVO) {
        itemView.tvQuestion.text = data.sentence
        itemView.tvAnswer.text = data.answer
        mData = data
    }
    override fun bindCount(count:Int) {
        itemView.tvCount.text = "$count ). "
    }
}