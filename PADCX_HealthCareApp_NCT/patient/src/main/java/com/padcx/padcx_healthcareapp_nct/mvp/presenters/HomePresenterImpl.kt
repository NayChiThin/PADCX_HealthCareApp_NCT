package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.padcx_healthcareapp_nct.mvp.views.HomeView
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl: HomePresenter,AbstractBasePresenter<HomeView>() {

    private val mHomeModel : HomeModel =
        HomeModelImpl

    override fun onUiReady(lifecycleOwner:LifecycleOwner,patientId:String) {
        loadDataFromAPI()
        loadDataFromDb(lifecycleOwner,patientId)
    }

    private fun loadDataFromDb(lifecycleOwner: LifecycleOwner,patientId:String) {
        mHomeModel.getSpecialitiesFromDb()
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show speciality list in ui
                }
            })
        mHomeModel.getRecentDoctorsFromDb(patientId)
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show recent doctor list in ui
                }
            })
    }

    private fun loadDataFromAPI() {
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
            "Bob",
            onSuccess = {
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
        // navigate to request consult screen
    }
}