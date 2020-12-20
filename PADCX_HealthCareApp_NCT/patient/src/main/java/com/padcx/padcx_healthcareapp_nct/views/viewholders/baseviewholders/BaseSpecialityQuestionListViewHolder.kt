package com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders

import android.view.View
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.shared.data.vos.QuestionVO

abstract class BaseSpecialityQuestionListViewHolder(itemView:View): BaseViewHolder<QuestionVO>(itemView) {
    abstract fun bindCount(count:Int)
}