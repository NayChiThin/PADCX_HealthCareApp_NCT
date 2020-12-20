package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.padcx.padcx_healthcareapp_nct.adapters.PatientDetailListAdapter
import com.padcx.padcx_healthcareapp_nct.adapters.SpecialityQuestionListAdapter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.FillCaseSummaryPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls.FillCaseSummaryPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.FillCaseSummaryView
import com.padcx.padcx_healthcareapp_nct.views.components.CustomDialogCaseSummary
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.utils.EXTRA_SPECIALITY_NAME
import com.padcx.shared.utils.MyViewPagerAdapter
import kotlinx.android.synthetic.main.activity_fill_case_summary.*
import kotlinx.android.synthetic.main.general_question_page.*
import kotlinx.android.synthetic.main.speciality_question_page.*

class FillCaseSummaryActivity : BaseActivity(),FillCaseSummaryView {

    companion object {
        private val layouts = arrayOf(
            R.layout.general_question_page,
            R.layout.speciality_question_page
        )
        fun newIntent(context: Context,specialityName:String):Intent {
            val intent = Intent(context,FillCaseSummaryActivity::class.java)
            intent.putExtra(EXTRA_SPECIALITY_NAME,specialityName)
            return intent
        }
    }
    private lateinit var myViewPagerAdapter : MyViewPagerAdapter
    private lateinit var mPresenter : FillCaseSummaryPresenter
    private lateinit var specialityName: String
    private var generalQuestions : List<QuestionVO> = arrayListOf()
    private lateinit var specialityQuestions : List<QuestionVO>
    private lateinit var mSpecialQuestionsAdapter : SpecialityQuestionListAdapter
    private lateinit var mPatientDetailAdapter : PatientDetailListAdapter
    private lateinit var patientName : String
    private lateinit var dialog : CustomDialogCaseSummary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_case_summary)

        specialityName = intent.getStringExtra(EXTRA_SPECIALITY_NAME)?:""
        setUpPresenter()
        mPresenter.onUiReady(specialityName)

    }
    private fun setUpPatientDetailRecyclerView() {
        mPatientDetailAdapter = PatientDetailListAdapter()
        val patientLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvPatientDetail.layoutManager = patientLayoutManager
        rvPatientDetail.adapter = mPatientDetailAdapter
    }
    private fun setUpSpecialityQuestionRecyclerView(){
        mSpecialQuestionsAdapter = SpecialityQuestionListAdapter()
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvSpecialityQuestion.layoutManager = layoutManager
        rvSpecialityQuestion.adapter = mSpecialQuestionsAdapter
    }
    private fun setUpViewPager() {
        myViewPagerAdapter = MyViewPagerAdapter(this,layouts)
        viewPager.adapter = myViewPagerAdapter
        setUpPatientDetailRecyclerView()
        checkAnswer()
        setUpListener()
        scrollView.visibility = View.VISIBLE


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        ivCheck.setImageResource(R.drawable.ic_check_circle_fill)
                        ivPath.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                        ivCheck2.setImageResource(R.drawable.ic_check_circle_gray)
                        ivPath2.setBackgroundColor(resources.getColor(R.color.colorGray))
                        checkAnswer()
                    }
                    1 -> {
                        ivCheck2.setImageResource(R.drawable.ic_check_circle_fill)
                        ivPath2.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                        ivCheck.setImageResource(R.drawable.ic_check_circle_gray)
                        ivPath.setBackgroundColor(resources.getColor(R.color.colorGray))
                        setUpSpecialityQuestionRecyclerView()
                        mSpecialQuestionsAdapter.setNewData(specialityQuestions.toMutableList())
                        btnMakeRequest.setOnClickListener {
                            for(question in specialityQuestions) {
                                val position = specialityQuestions.indexOf(question)
                                specialityQuestions[position].answer = mSpecialQuestionsAdapter.answerList[position]
                            }
                            // show confirm dialog
                            mPresenter.onTapMakeRequest(generalQuestions,specialityQuestions)
                        }
                    }
                }
            }

        })
    }
    private fun setUpListener() {
        btnContinue.setOnClickListener {
            for(question in generalQuestions) {
                if(question.answer.isNullOrBlank()) {
                    when(question.name) {
                        "dob" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = etDay.text.toString()+" "+etMonth.text.toString()+" "+etYear.text.toString()
                        }
                        "bloodtype" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = when(rdBloodType.checkedRadioButtonId) {
                                0 -> "A"
                                1 -> "B"
                                2 -> "AB"
                                3 -> "O"
                                else -> ""
                            }
                        }
                        "height" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = etHeight.text.toString()+ " ft"
                        }
                        "weight" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = etWeight.text.toString() + " lb"
                        }
                        "bloodpressure" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = etBloodPressure.text.toString() + " mmHg"
                        }
                        "medicine" -> {
                            generalQuestions[generalQuestions.indexOf(question)].answer = etMedicine.text.toString()
                        }
                    }
                }
            }
            viewPager.currentItem = 1
        }
    }
    private fun checkAnswer() {
        var questionList : MutableList<QuestionVO> = arrayListOf()
        val question = QuestionVO()
        question.name = "Name"
        question.answer = patientName
        question.type = "General"
        questionList.add(question)
        for(question in generalQuestions) {
            if(!question.answer.isNullOrBlank()) {
                questionList.add(question)
                when(question.name) {
                    "dob" -> {
                        tvDob.visibility = View.GONE
                        etDay.visibility = View.GONE
                        etMonth.visibility = View.GONE
                        etYear.visibility = View.GONE
                    }
                    "bloodtype" -> {
                        tvBloodType.visibility = View.GONE
                        rdBloodType.visibility = View.GONE
                    }
                    "height" -> {
                        tvHeight.visibility = View.GONE
                        etHeight.visibility = View.GONE
                        tvHeightUnit.visibility = View.GONE
                    }
                    "weight" -> {
                        tvWeight.visibility = View.GONE
                        etWeight.visibility = View.GONE
                        tvWeightUnit.visibility = View.GONE
                    }
                    "bloodpressure" -> {
                        tvBloodPressure.visibility = View.GONE
                        etBloodPressure.visibility = View.GONE
                        tvBloodPressureUnit.visibility = View.GONE
                    }
                    "medicine" -> {
                        tvMedicine.visibility = View.GONE
                        etMedicine.visibility = View.GONE
                    }
                }
            }
        }
        mPatientDetailAdapter.setNewData(questionList)
    }
    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(FillCaseSummaryPresenterImpl::class.java)
        mPresenter.initPresenter(this)

    }

    override fun displayCaseSummary(generalQuestions:List<QuestionVO>,specialityQuestions:List<QuestionVO>) {
        dialog = CustomDialogCaseSummary(this,generalQuestions,specialityQuestions,this)
        dialog.show()
    }

    override fun displayGeneralQuestion(questions: List<QuestionVO>) {
        generalQuestions = questions
    }

    override fun displaySpecialityQuestion(questions: List<QuestionVO>) {
        specialityQuestions = questions
    }

    override fun displayViewPager() {
        setUpViewPager()
    }

    override fun displayMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun displayUserName(name: String) {
        patientName = name
    }

    override fun navigateToHome() {
        finish()
    }

    override fun broadcastRequest(questions: List<QuestionVO>) {
        mPresenter.onTapConfirmDetail(questions,specialityName)
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}