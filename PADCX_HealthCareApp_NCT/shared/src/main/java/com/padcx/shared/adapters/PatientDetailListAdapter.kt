package com.padcx.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.R
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.views.viewholders.BasePatientDetailListViewHolder
import com.padcx.shared.views.viewholders.PatientDetailListViewHolder

class PatientDetailListAdapter: BaseRecyclerAdapter<BasePatientDetailListViewHolder, QuestionVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePatientDetailListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.general_question_items,parent,false)
        return PatientDetailListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasePatientDetailListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}