package com.padcx.doctor

import android.content.Context
import android.content.Intent
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
import com.padcx.shared.utils.EXTRA_DOCTOR_ID

class MainActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context,MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}