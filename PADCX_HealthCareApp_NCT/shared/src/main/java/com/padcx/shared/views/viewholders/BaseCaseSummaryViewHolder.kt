package com.padcx.shared.views.viewholders

import android.view.View
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.shared.data.vos.QuestionVO

abstract class BaseCaseSummaryViewHolder(itemView:View): BaseViewHolder<QuestionVO>(itemView) {
    abstract fun bindCount(count:Int)
}