package com.padcx.doctor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.adapters.CaseSummaryQuestionListAdapter
import com.padcx.doctor.adapters.PatientDetailListAdapter
import com.padcx.doctor.mvp.presenters.PatientDetailPresenter
import com.padcx.doctor.mvp.presenters.impls.PatientDetailPresenterImpl
import com.padcx.doctor.mvp.views.PatientDetailView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.utils.EXTRA_CONSULT_REQUEST
import kotlinx.android.synthetic.main.activity_patient_detail.*

class PatientDetailActivity : BaseActivity(),PatientDetailView {

    private lateinit var consultRequest : ConsultRequestVO
    private lateinit var mPatientDetailPresenter : PatientDetailPresenter
    private lateinit var mGeneralQuestionListAdapter : PatientDetailListAdapter
    private lateinit var mSpecialityQuestionListAdapter : CaseSummaryQuestionListAdapter
    private var generalQuestions : MutableList<QuestionVO> = arrayListOf()
    private var specialityQuestions : MutableList<QuestionVO> = arrayListOf()

    companion object {
        fun newIntent(context: Context,request:ConsultRequestVO):Intent {
            val intent = Intent(context,PatientDetailActivity::class.java)
            intent.putExtra(EXTRA_CONSULT_REQUEST,request)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)

        consultRequest = intent.getSerializableExtra(EXTRA_CONSULT_REQUEST) as ConsultRequestVO

        setUpPresenter()
        setUpRecyclerView()
        mPatientDetailPresenter.onUiReady(this)
        setUpListener()
    }
    private fun setUpListener() {
        btnStartConsult.setOnClickListener {
            mPatientDetailPresenter.onTapStartConsult(consultRequest)
        }
    }
    private fun setUpRecyclerView(){
        mGeneralQuestionListAdapter = PatientDetailListAdapter()
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvGeneralQuestion.adapter = mGeneralQuestionListAdapter
        rvGeneralQuestion.layoutManager = layoutManager

        mSpecialityQuestionListAdapter = CaseSummaryQuestionListAdapter()
        val specialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvSpecialityQuestion.adapter = mSpecialityQuestionListAdapter
        rvSpecialityQuestion.layoutManager = specialityLayoutManager
    }
    private fun setUpPresenter() {
        mPatientDetailPresenter = ViewModelProviders.of(this).get(PatientDetailPresenterImpl::class.java)
        mPatientDetailPresenter.initPresenter(this)
    }

    override fun displayPatientDetails() {
        val question = QuestionVO()
        question.name = "လူနာအမည်"
        question.answer = consultRequest.patient?.name
        generalQuestions.add(question)
        for(question in consultRequest.caseSummary?: arrayListOf()) {
            if(question.type == "General") {
                generalQuestions.add(question)
            }else {
                specialityQuestions.add(question)
            }
        }
        Glide.with(this)
            .load(consultRequest.patient?.profilephoto)
            .into(ivPatient)
        mGeneralQuestionListAdapter.setNewData(generalQuestions)
        mSpecialityQuestionListAdapter.setNewData(specialityQuestions)
    }

    override fun navigateToChat() {
        finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}