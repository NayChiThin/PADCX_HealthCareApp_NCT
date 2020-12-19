package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.views.viewholders.BasePatientDetailListViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.PatientDetailListViewHolder
import com.padcx.shared.data.vos.QuestionVO

class PatientDetailListAdapter: BaseRecyclerAdapter<BasePatientDetailListViewHolder, QuestionVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePatientDetailListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_detail_items,parent,false)
        return PatientDetailListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasePatientDetailListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}