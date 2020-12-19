package com.padcx.padcx_healthcareapp_nct.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.views.viewholders.BaseSpecialityQuestionListViewHolder
import com.padcx.padcx_healthcareapp_nct.views.viewholders.SpecialityQuestionListViewHolder
import com.padcx.shared.data.vos.QuestionVO

class SpecialityQuestionListAdapter():
    BaseRecyclerAdapter<BaseSpecialityQuestionListViewHolder, QuestionVO>()  {

    var answerList : MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSpecialityQuestionListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.speciality_question_items,parent,false)
        return SpecialityQuestionListViewHolder(view,answerList)
    }

    override fun onBindViewHolder(holder: BaseSpecialityQuestionListViewHolder, position: Int) {
        answerList.add(mData[position].answer?:"")
        holder.bindData(mData[position])
        holder.bindCount(position+1)
    }
    fun getItem(position:Int):QuestionVO {
        return mData[position]
    }

}