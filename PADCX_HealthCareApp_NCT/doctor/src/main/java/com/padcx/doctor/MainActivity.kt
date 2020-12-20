package com.padcx.doctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.adapters.ConsultRequestListAdapter
import com.padcx.doctor.adapters.PreviousConsultListAdapter
import com.padcx.doctor.mvp.presenters.HomePresenter
import com.padcx.doctor.mvp.presenters.MedicineGuidePresenter
import com.padcx.doctor.mvp.presenters.RegisterPresenter
import com.padcx.doctor.mvp.presenters.impls.HomePresenterImpl
import com.padcx.doctor.mvp.presenters.impls.MedicineGuidePresenterImpl
import com.padcx.doctor.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.doctor.mvp.views.MainView
import com.padcx.doctor.mvp.views.MedicineGuideView
import com.padcx.doctor.mvp.views.RegisterView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.*
import com.padcx.shared.utils.EXTRA_DOCTOR_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainView {

    private lateinit var mPreviousConsultListAdapter : PreviousConsultListAdapter
    private lateinit var mConsultRequestListAdapter : ConsultRequestListAdapter
    private lateinit var mMainPresenter : HomePresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context,MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
        setUpRecyclerView()

        mMainPresenter.onUiReady(this)

    }
    private fun setUpPresenter(){
        mMainPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mMainPresenter.initPresenter(this)
    }
    private fun setUpRecyclerView() {
        mPreviousConsultListAdapter = PreviousConsultListAdapter(mMainPresenter)
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvPreviousConsult.adapter = mPreviousConsultListAdapter
        rvPreviousConsult.layoutManager = layoutManager

        mConsultRequestListAdapter = ConsultRequestListAdapter(mMainPresenter)
        val requestLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvConsultRequest.adapter = mConsultRequestListAdapter
        rvConsultRequest.layoutManager = requestLayoutManager
    }

    override fun navigateToPatientDetail(consultRequest: ConsultRequestVO) {
        startActivity(PatientDetailActivity.newIntent(this,consultRequest))
    }

    override fun navigateToChat(consult: ConsultVO) {
        startActivity(ChatActivity.newIntent(this,consult))
    }

    override fun displayDoctorProfile(name: String, photo: String) {
        tvName.text = name
        Glide.with(applicationContext)
            .load(photo)
            .into(ivDoctor)
    }

    override fun displayConsultRequests(requests:List<ConsultRequestVO>) {
        mConsultRequestListAdapter.setNewData(requests.toMutableList())
    }

    override fun displayPreviousConsults(consults:List<ConsultVO>) {
        mPreviousConsultListAdapter.setNewData(consults.toMutableList())
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }

}