package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.delegates.SpecialityItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseSpecialityListViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.SpecialityListViewHolder
import com.padcx.shared.data.vos.SpecialityVO

class SpecialityListAdapter(delegate:SpecialityItemDelegate) : BaseRecyclerAdapter<BaseSpecialityListViewHolder, SpecialityVO>() {
    val mDelegate : SpecialityItemDelegate = delegate
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSpecialityListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.speciality_items,parent,false)
        return SpecialityListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BaseSpecialityListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

}