package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.doctor.R
import com.padcx.doctor.delegates.ConsultRequestItemDelegate
import com.padcx.doctor.views.viewholders.BaseConsultRequestListViewHolder
import com.padcx.doctor.views.viewholders.ConsultRequestListViewHolder
import com.padcx.shared.data.vos.ConsultRequestVO

class ConsultRequestListAdapter(delegate:ConsultRequestItemDelegate):
    BaseRecyclerAdapter<BaseConsultRequestListViewHolder, ConsultRequestVO>() {
    private val mDelegate = delegate
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseConsultRequestListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.consult_request_items,parent,false)
        return ConsultRequestListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BaseConsultRequestListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}