package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BasePatientDetailListViewHolder
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.patient_detail_items.view.*

class PatientDetailListViewHolder(itemView:View):
    BasePatientDetailListViewHolder(itemView) {
    override fun bindData(data: QuestionVO) {
        mData = data
        itemView.tvQuestion.text = data.name
        itemView.tvAnswer.text = data.answer
    }
}