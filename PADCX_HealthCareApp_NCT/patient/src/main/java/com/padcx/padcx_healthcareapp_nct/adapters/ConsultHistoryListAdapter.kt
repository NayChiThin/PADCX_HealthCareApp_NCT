package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultHistoryItemDelegate
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultationItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.ConsultHistoryViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseConsultHistoryViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vos.ConsultVO

class ConsultHistoryListAdapter(val delegate:ConsultHistoryItemDelegate):BaseRecyclerAdapter<BaseConsultHistoryViewHolder,ConsultVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseConsultHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.consult_history_items,parent,false)
        return ConsultHistoryViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: BaseConsultHistoryViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

}