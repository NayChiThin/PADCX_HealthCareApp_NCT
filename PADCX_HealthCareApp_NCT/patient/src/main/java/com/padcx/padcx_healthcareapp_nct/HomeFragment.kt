package com.padcx.padcx_healthcareapp_nct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcx.padcx_healthcareapp_nct.adapters.ConsultationListAdapter
import com.padcx.padcx_healthcareapp_nct.adapters.RecentDoctorListAdapter
import com.padcx.padcx_healthcareapp_nct.adapters.SpecialityListAdapter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.HomePresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.HomePresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.HomeView
import com.padcx.padcx_healthcareapp_nct.views.components.CustomDialogConfirmRequest
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),HomeView {

    val patientId = ""
    private lateinit var mHomePresenter : HomePresenter
    private lateinit var mSpecialityListAdapter : SpecialityListAdapter
    private lateinit var mRecentDoctorListAdapter : RecentDoctorListAdapter
    private lateinit var dialog : CustomDialogConfirmRequest
    private lateinit var mConsultListAdapter : ConsultationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()
        mHomePresenter.onUiReady(this)
    }
    private fun setUpPresenter() {
        mHomePresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mHomePresenter.initPresenter(this)
    }
    private fun setUpRecyclerView() {
        mSpecialityListAdapter = SpecialityListAdapter(mHomePresenter)
        val layoutManager = GridLayoutManager(context,2)
        rvSpeciality.adapter = mSpecialityListAdapter
        rvSpeciality.layoutManager = layoutManager

        mRecentDoctorListAdapter = RecentDoctorListAdapter(mHomePresenter)
        val doctorLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvRecentConsult.adapter = mRecentDoctorListAdapter
        rvRecentConsult.layoutManager = doctorLayoutManager

        mConsultListAdapter = ConsultationListAdapter(mHomePresenter)
        val consultLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvConsultation.adapter = mConsultListAdapter
        rvConsultation.layoutManager = consultLayoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun displaySpecialityList(specialities:List<SpecialityVO>) {
        mSpecialityListAdapter.setNewData(specialities.toMutableList())
    }

    override fun displayRecentDoctorList(doctors:List<DoctorVO>) {
        if(doctors.isEmpty()) {
            rlRecentConsult.visibility = View.GONE
        }else {
            mRecentDoctorListAdapter.setNewData(doctors.toMutableList())
        }
    }

    override fun displayRequestDialog(specialityName:String) {
        dialog = CustomDialogConfirmRequest(this.requireContext(),this,specialityName)
        dialog.show()
    }

    override fun navigateToConsultation(consultId: String) {
    }

    override fun navigateToAddCaseSummary(specialityName: String) {
        startActivity(this.context?.let { FillCaseSummaryActivity.newIntent(it,specialityName) })
        dialog.dismiss()
    }


    override fun showError(error: String) {
    }
}