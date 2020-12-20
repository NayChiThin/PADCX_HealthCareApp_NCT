package com.padcx.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenters.HomePresenter
import com.padcx.doctor.mvp.views.MainView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl: HomePresenter,AbstractBasePresenter<MainView>() {
    private val mHomeModel : HomeModel = HomeModelImpl
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private lateinit var name : String
    private lateinit var photo : String
    private lateinit var doctorId : String
    private lateinit var specialityName : String

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        doctorId = mAuthenticationModel.getUserId()
        name = mAuthenticationModel.getUserName()
        photo = mAuthenticationModel.getUserPhoto()
        mHomeModel.getConsultRequest(
            onSuccess = {
                mHomeModel.getDoctorById(doctorId,
                    onSuccess = {
                        specialityName = it.speciality?:""
                        mHomeModel.getConsultRequestFromDb(specialityName)
                            .observe(lifecycleOwner, Observer {consultList->
                                mView.displayConsultRequests(consultList)
                            })
                    },
                    onFailure = {
                        mView.showError(it)
                    })
            },
            onFailure = {
                mView.showError(it)
            }
        )
        mHomeModel.getPreviousConsultations(doctorId,
        onSuccess = {
            mHomeModel.getPreviousConsultationsFromDb(doctorId)
                .observe(lifecycleOwner, Observer {
                    mView.displayDoctorProfile(name,photo)
                    mView.displayPreviousConsults(it)
                })
        },
        onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapPrescription(consult: ConsultVO) {
    }

    override fun onTapCaseSummary(consult: ConsultVO) {
    }

    override fun onTapConsultNote(consult: ConsultVO) {
    }

    override fun onTapSendMessage(consult: ConsultVO) {
        mView.navigateToChat(consult)
    }

    override fun onTapAccept(consultRequest: ConsultRequestVO) {
        mView.navigateToPatientDetail(consultRequest)
    }
}