package com.padcx.shared

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.adapters.CaseSummaryQuestionListAdapter
import com.padcx.shared.adapters.PatientDetailListAdapter
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.PatientDetailsPresenter
import com.padcx.shared.mvp.presenters.PatientDetailsPresenterImpl
import com.padcx.shared.mvp.views.PatientDetailsView
import com.padcx.shared.utils.EXTRA_CONSULTATION
import kotlinx.android.synthetic.main.activity_patient_details.*

class PatientDetailsActivity : BaseActivity(),PatientDetailsView {

    companion object {
        fun newIntent(context: Context,consult:ConsultVO):Intent {
            val intent = Intent(context,PatientDetailsActivity::class.java)
            intent.putExtra(EXTRA_CONSULTATION,consult)
            return intent
        }
    }

    private lateinit var mPatientDetailsPresenter : PatientDetailsPresenter
    private lateinit var mDetailListAdapter : PatientDetailListAdapter
    private lateinit var mSpecialityListAdapter : CaseSummaryQuestionListAdapter
    private lateinit var consult : ConsultVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        consult = intent.getSerializableExtra(EXTRA_CONSULTATION) as ConsultVO

        setUpListener()
        setUpPresenter()
        setUpRecyclerView()

        mPatientDetailsPresenter.onUiReady()
    }
    private fun setUpListener() {
        ivBack.setOnClickListener {
            mPatientDetailsPresenter.onTapBack()
        }
    }
    private fun setUpPresenter() {
        mPatientDetailsPresenter = ViewModelProviders.of(this).get(PatientDetailsPresenterImpl::class.java)
        mPatientDetailsPresenter.initPresenter(this)
    }
    private fun setUpRecyclerView() {
        mDetailListAdapter = PatientDetailListAdapter()
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvGeneralQuestion.adapter = mDetailListAdapter
        rvGeneralQuestion.layoutManager = layoutManager

        mSpecialityListAdapter = CaseSummaryQuestionListAdapter()
        val specialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvSpecialityQuestion.adapter = mSpecialityListAdapter
        rvSpecialityQuestion.layoutManager = specialityLayoutManager
    }

    override fun displayDetails() {
        val generalQuestions : MutableList<QuestionVO> = arrayListOf()
        val specialityQuestions : MutableList<QuestionVO> = arrayListOf()
        val question = QuestionVO()
        question.name = "လူနာအမည်"
        question.answer = consult.patient?.name
        for(question in consult.caseSummary?: arrayListOf()) {
            if(question.type == "General") {
                generalQuestions.add(question)
            }else {
                specialityQuestions.add(question)
            }
        }
        mDetailListAdapter.setNewData(generalQuestions)
        mSpecialityListAdapter.setNewData(specialityQuestions)
    }

    override fun navigateToChat() {
        finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}