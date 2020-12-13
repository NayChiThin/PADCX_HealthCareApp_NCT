package com.padcx.doctor

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.mvp.presenters.MedicineGuidePresenter
import com.padcx.doctor.mvp.presenters.RegisterPresenter
import com.padcx.doctor.mvp.presenters.impls.MedicineGuidePresenterImpl
import com.padcx.doctor.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.doctor.mvp.views.MedicineGuideView
import com.padcx.doctor.mvp.views.RegisterView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.data.vos.RoutineVO

class MainActivity : BaseActivity(),RegisterView,MedicineGuideView {

    private lateinit var mRegisterPresenter : RegisterPresenter
    private lateinit var mMedicineGuidePresenter: MedicineGuidePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
    }
    private fun setUpPresenter() {
        mRegisterPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        mRegisterPresenter.initPresenter(this)

        mMedicineGuidePresenter = ViewModelProviders.of(this).get(MedicineGuidePresenterImpl::class.java)
        mMedicineGuidePresenter.initPresenter(this)


        val prescriptionList = arrayListOf<PrescriptionVO>()
        val prescriptOne = PrescriptionVO()
        prescriptOne.count = 9
        prescriptOne.medicine
        val medicineOne = MedicineVO()
        medicineOne.name = "Test medicine"
        medicineOne.cost = 20F
        prescriptOne.medicine = medicineOne
        val routineOne = RoutineVO()
        routineOne.day = "test days"
        routineOne.time = "test times"
        routineOne.timesPerDay = 3
        routineOne.totalDays = 3
        prescriptOne.routine = routineOne
        prescriptionList.add(prescriptOne)

        mMedicineGuidePresenter.onTapFinishConsult(DoctorVO("Test Doc","09123843","Dentist","","Hello","Graduated Master Degree"),"Bob",prescriptionList)

//        mRegisterPresenter.onTapRegister(DoctorVO("Test Doc","09123843","Dentist","","Hello","Graduated Master Degree"))
    }

    override fun showError(error: String) {
        Snackbar.make(findViewById(R.id.relativeLayout),error,Snackbar.LENGTH_LONG)
    }
}