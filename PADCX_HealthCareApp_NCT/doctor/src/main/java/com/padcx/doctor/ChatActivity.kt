package com.padcx.doctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.doctor.adapters.CaseSummaryQuestionListAdapter
import com.padcx.doctor.adapters.MessageListAdapter
import com.padcx.doctor.adapters.PatientDetailListAdapter
import com.padcx.doctor.mvp.presenters.ChatPresenter
import com.padcx.doctor.mvp.presenters.ChatPresenterImpl
import com.padcx.doctor.mvp.views.ChatView
import com.padcx.shared.PatientDetailsActivity
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.utils.EXTRA_CONSULTATION
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity(),ChatView {
    private lateinit var mChatPresenter: ChatPresenter
    private lateinit var mMessageListAdapter: MessageListAdapter
    private lateinit var consult: ConsultVO
    private lateinit var mSpecialityQuestionListAdapter: CaseSummaryQuestionListAdapter
    private lateinit var mPatientDetailListAdapter: PatientDetailListAdapter

    companion object {
        fun newIntent(context: Context, consult: ConsultVO): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(EXTRA_CONSULTATION, consult)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        consult = intent.getSerializableExtra(EXTRA_CONSULTATION) as ConsultVO

        tvPatientName.text = consult.patient?.name

        setUpPresenter()
        setUpListener()
        mChatPresenter.onUiReady(this, consult)
    }

    private fun setUpListener() {
        tvSeeMore.setOnClickListener {
            mChatPresenter.onTapSeeMore()
        }
        ivBack.setOnClickListener {
            mChatPresenter.onTapBack()
        }
        ivImageMessage.setOnClickListener {
            mChatPresenter.onTapAttach()
        }
        ivSendMessage.setOnClickListener {
            val text = etMessage.text.toString()
            if (text.isNotEmpty()) {
                mChatPresenter.onTapSend(consult.id, etMessage.text.toString())
                etMessage.setText("")
            } else {
                Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpPresenter() {
        mChatPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mChatPresenter.initPresenter(this)
    }

    override fun setUpRecyclerView(userName: String) {
        mMessageListAdapter = MessageListAdapter(userName)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvChat.adapter = mMessageListAdapter
        rvChat.layoutManager = layoutManager

        mPatientDetailListAdapter = PatientDetailListAdapter()
        val detailLayoutManger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPatientDetail.adapter = mPatientDetailListAdapter
        rvPatientDetail.layoutManager = detailLayoutManger

        mSpecialityQuestionListAdapter = CaseSummaryQuestionListAdapter()
        val specialityLayoutManager = object : LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        ) {
            override fun canScrollVertically(): Boolean = false
        }
        rvSpecialityQuestion.adapter = mSpecialityQuestionListAdapter
        rvSpecialityQuestion.layoutManager = specialityLayoutManager
    }

    override fun displayMessages(messages: List<MessageVO>) {
        mMessageListAdapter.setNewData(messages.toMutableList())
    }

    override fun displayPatientDetails(questions: List<QuestionVO>) {
        mPatientDetailListAdapter.setNewData(questions.toMutableList())
    }

    override fun displaySpecialityDetails(questions: List<QuestionVO>) {
        mSpecialityQuestionListAdapter.setNewData(questions.toMutableList())
    }

    override fun navigateToHome() {
        finish()
    }

    override fun displayLatestPositionScrollView() {
        scrollView.scrollTo(0,scrollView.bottom)
    }

    override fun navigateToPatientDetails() {
        startActivity(PatientDetailsActivity.newIntent(this, consult))
    }

    override fun showError(error: String) {

    }
}