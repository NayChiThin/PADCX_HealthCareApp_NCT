package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultationItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.BaseConsultationListViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.ConsultationListViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vos.DoctorVO

class ConsultationListAdapter(private val mDelegate:ConsultationItemDelegate):BaseRecyclerAdapter<BaseConsultationListViewHolder,DoctorVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseConsultationListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.consultation_item,parent,false)
        return ConsultationListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BaseConsultationListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}