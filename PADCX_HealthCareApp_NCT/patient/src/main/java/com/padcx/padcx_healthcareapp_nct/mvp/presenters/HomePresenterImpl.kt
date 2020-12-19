package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.padcx_healthcareapp_nct.mvp.views.HomeView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl: HomePresenter,AbstractBasePresenter<HomeView>() {

    private val mHomeModel : HomeModel = HomeModelImpl
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private var patientId = ""

    override fun onUiReady(lifecycleOwner:LifecycleOwner) {
        patientId = mAuthenticationModel.getUserId()
        loadDataFromAPI(patientId)
        loadDataFromDb(lifecycleOwner)
    }

    private fun loadDataFromDb(lifecycleOwner: LifecycleOwner) {
        mHomeModel.getSpecialitiesFromDb()
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show speciality list in ui
                    mView.displaySpecialityList(it)
                }
            })
        mHomeModel.getRecentDoctorsFromDb(patientId)
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show recent doctor list in ui
                    mView.displayRecentDoctorList(it)
                }
            })

        mHomeModel.getConsultByPatientIdFromDb(patientId)
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show accepted consults
                    mView.displayAcceptedConsult(it)
                }
            })
    }

    private fun loadDataFromAPI(patientId:String) {
        // get specialities
        mHomeModel.getSpecialities(
            onSuccess = {

            },
            onFailure = {
                mView.showError(it)
            }
        )
        // get recent doctors
        mHomeModel.getRecentDoctors(
            patientId,
            onSuccess = {
            },
            onFailure = {
                mView.showError(it)
            }
        )
        // get accepted consult
        mHomeModel.getConsultation(
            onSuccess = {
//                mView.displayAcceptedConsult(it)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun onTapStartConsult() {
        // navigate to consult screen
    }

    override fun onTapRecentDoctor(doctorName: String,specialityName:String) {
        // navigate to request consult screen with doctor name
    }

    override fun onTapSpeciality(specialityName: String) {
        mView.displayRequestDialog(specialityName)
    }

    override fun onTapDoctor(doctorId: String) {

    }
}