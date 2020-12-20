package com.padcx.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.R
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.views.viewholders.BaseCaseSummaryViewHolder
import com.padcx.shared.views.viewholders.CaseSummaryViewHolder

class CaseSummaryQuestionListAdapter: BaseRecyclerAdapter<BaseCaseSummaryViewHolder, QuestionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCaseSummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.questions_items,parent,false)
        return CaseSummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseCaseSummaryViewHolder, position: Int) {
        holder.bindData(mData[position])
        holder.bindCount(position+1)
    }
}