package com.padcx.padcx_healthcareapp_nct.views.components

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.adapters.CaseSummaryQuestionListAdapter
import com.padcx.padcx_healthcareapp_nct.adapters.PatientDetailListAdapter
import com.padcx.padcx_healthcareapp_nct.adapters.SpecialityQuestionListAdapter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.FillCaseSummaryPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.FillCaseSummaryPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.FillCaseSummaryView
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.confirm_case_summary_dialog.*

class CustomDialogCaseSummary(context:Context,val generalQuestions:List<QuestionVO>,val specialityQuestions:List<QuestionVO>,val view:FillCaseSummaryView):Dialog(context) {
    init {
        setCancelable(true)
    }

    private lateinit var mGeneralQuestionAdapter : PatientDetailListAdapter
    private lateinit var mSpecialityQuestionAdapter : CaseSummaryQuestionListAdapter
    private var questions : MutableList<QuestionVO> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.confirm_case_summary_dialog)
        setUpRecyclerView()
        setUpData()

        btnConfirmRequest.setOnClickListener {
            questions.addAll(generalQuestions)
            questions.addAll(specialityQuestions)
            dismiss()
            view.broadcastRequest(questions)
        }
    }
    private fun setUpData() {
        mGeneralQuestionAdapter.setNewData(generalQuestions.toMutableList())
        mSpecialityQuestionAdapter.setNewData(specialityQuestions.toMutableList())
    }
    private fun setUpRecyclerView() {
        mGeneralQuestionAdapter = PatientDetailListAdapter()
        val generalLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvPatientDetail.adapter = mGeneralQuestionAdapter
        rvPatientDetail.layoutManager = generalLayoutManager

        mSpecialityQuestionAdapter = CaseSummaryQuestionListAdapter()
        val specialityLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvSpecialityQuestion.adapter = mSpecialityQuestionAdapter
        rvSpecialityQuestion.layoutManager = specialityLayoutManager
    }
}