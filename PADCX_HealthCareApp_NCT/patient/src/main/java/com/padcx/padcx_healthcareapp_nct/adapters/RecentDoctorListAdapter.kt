package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.delegates.DoctorItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseRecentDoctorListViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.RecentDoctorListViewHolder
import com.padcx.shared.data.vos.DoctorVO

class RecentDoctorListAdapter(delegate:DoctorItemDelegate):
    BaseRecyclerAdapter<BaseRecentDoctorListViewHolder, DoctorVO>() {
    private val mDelegate : DoctorItemDelegate = delegate
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecentDoctorListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_doctor_items,parent,false)
        return RecentDoctorListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BaseRecentDoctorListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}