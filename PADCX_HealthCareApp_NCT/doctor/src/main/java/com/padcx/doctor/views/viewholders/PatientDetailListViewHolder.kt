package com.padcx.doctor.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.general_question_items.view.*

class PatientDetailListViewHolder(itemView:View):BasePatientDetailListViewHolder(itemView) {
    override fun bindData(data: QuestionVO) {
        mData = data
        itemView.tvQuestion.text = data.name
        itemView.tvAnswer.text = data.answer
    }
}