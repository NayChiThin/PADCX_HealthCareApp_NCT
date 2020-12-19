package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.doctor.R
import com.padcx.doctor.delegates.ConsultationItemDelegate
import com.padcx.doctor.views.viewholders.BasePreviousConsultListViewHolder
import com.padcx.doctor.views.viewholders.PreviousConsultListViewHolder
import com.padcx.shared.data.vos.ConsultVO

class PreviousConsultListAdapter(private val mDelegate:ConsultationItemDelegate):
    BaseRecyclerAdapter<BasePreviousConsultListViewHolder, ConsultVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePreviousConsultListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.previous_consult_items,parent,false)
        return PreviousConsultListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BasePreviousConsultListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}