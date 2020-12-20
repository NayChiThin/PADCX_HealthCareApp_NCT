package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.doctor.R
import com.padcx.doctor.views.viewholders.BaseCaseSummaryViewHolder
import com.padcx.doctor.views.viewholders.CaseSummaryViewHolder
import com.padcx.shared.data.vos.QuestionVO

class CaseSummaryQuestionListAdapter: BaseRecyclerAdapter<BaseCaseSummaryViewHolder, QuestionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCaseSummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_speciality_question_items,parent,false)
        return CaseSummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseCaseSummaryViewHolder, position: Int) {
        holder.bindData(mData[position])
        holder.bindCount(position)
    }
}