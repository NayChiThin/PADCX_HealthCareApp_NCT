package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.speciality_question_items.view.*


class SpecialityQuestionListViewHolder(itemView:View,var answerList:MutableList<String>):BaseSpecialityQuestionListViewHolder(itemView) {
    init {
        itemView.etAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                answerList[adapterPosition] = itemView.etAnswer.text.toString()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
    override fun bindData(data: QuestionVO) {
        itemView.tvQuestion.text = data.sentence
        itemView.etAnswer.setText(data.answer)

        mData = data
    }
    override fun bindCount(count:Int) {
        itemView.tvCount.text = "$count ). "
    }
}