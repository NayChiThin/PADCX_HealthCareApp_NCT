package com.padcx.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.views.ChatView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.ChatModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.ChatModelImpl
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl:ChatPresenter,AbstractBasePresenter<ChatView>() {

    private lateinit var userName :String
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mChatModel : ChatModel = ChatModelImpl
    private val patientDetails : MutableList<QuestionVO> = arrayListOf()
    private val specialityDetails : MutableList<QuestionVO> = arrayListOf()
    private var messages : MutableList<MessageVO> = arrayListOf()
    private lateinit var lifecycleOwner : LifecycleOwner

    override fun onTapBack() {
        mView.navigateToHome()
    }

    override fun onTapSend(consultId:String,text:String) {
        val message = MessageVO()
        message.sender = userName
        message.text = text
        message.date = ""
        message.image = ""
        val calender = Calendar.getInstance()
        val format = SimpleDateFormat("HH:mm")
        message.time = format.format(calender.time)
        mChatModel.saveMessages(consultId,message,
        onSuccess = {
            mView.displayLatestPositionScrollView()
        },
        onFailure = {
            mView.showError(it)
        })

    }

    override fun onTapAttach() {
        mView.showError("Function not available at the moment")
    }

    override fun onTapSeeMore() {
        mView.navigateToPatientDetails()
    }

    override fun onUiReady(owner:LifecycleOwner,consult: ConsultVO) {
        lifecycleOwner = owner
        userName = mAuthenticationModel.getUserName()
        mView.setUpRecyclerView(userName)

        mChatModel.getMessages(consult.id,
        onSuccess = {
//            messages = it.toMutableList()
//            mView.displayMessages(messages)
        },
        onFailure = {
            mView.showError(it)
        })
        mChatModel.getMessagesFromDb()
            .observe(lifecycleOwner, androidx.lifecycle.Observer {
                it?.let {
                    mView.displayMessages(it)
                    mView.displayLatestPositionScrollView()
                }
            })
        val question = QuestionVO()
        question.name = "လူနာအမည်"
        question.answer = consult.patient?.name
        patientDetails.add(question)
        for(question in consult.caseSummary?: arrayListOf()) {
            if(question.type == "General") {
                patientDetails.add(question)
            }else {
                specialityDetails.add(question)
            }
        }
        mView.displayPatientDetails(patientDetails)
        mView.displaySpecialityDetails(specialityDetails)
    }
}