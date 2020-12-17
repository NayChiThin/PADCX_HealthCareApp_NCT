package com.padcx.doctor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.mvp.presenters.CreateAccountPresenter
import com.padcx.doctor.mvp.presenters.impls.CreateAccountPresenterImpl
import com.padcx.doctor.mvp.views.CreateAccountView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : BaseActivity(),CreateAccountView {

    private lateinit var mCreateAccountPresenter : CreateAccountPresenter

    companion object {
        fun newIntent(context:Context) :Intent{
            return Intent(context,CreateAccountActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        setUpPresenter()
        setUpListener()
    }
    private fun setUpListener() {

        rbtnMale.setOnClickListener(View.OnClickListener {
            if(!it.isSelected) {
                it.isSelected = true
                rbtnFemale.isSelected = false
            }
        })
        rbtnFemale.setOnClickListener(View.OnClickListener {
            if(!it.isSelected) {
                it.isSelected = true
                rbtnMale.isSelected = false
            }
        })
        btnCreateAccount.setOnClickListener {
            val doctor = DoctorVO()
            doctor.name = etName.text.toString()
            doctor.phonenumber = etPhoneNumber.text.toString()
            doctor.certificate = etCertificate.text.toString()
            doctor.description = etDescription.text.toString()
            doctor.speciality = spinnerSpeciality.selectedItem.toString()
            doctor.address = etAddress.text.toString()
            if(rbtnMale.isSelected) {
                doctor.gender = "Male"
            }else {
                doctor.gender = "Female"
            }
            doctor.experience = etExperience.text.toString()
            doctor.dob = etDay.text.toString()+" "+etMonth.text.toString()+" "+etYear.text.toString()
            mCreateAccountPresenter.onTapCreateAccount(doctor)
        }
    }

    private fun setUpPresenter() {
        mCreateAccountPresenter = ViewModelProviders.of(this).get(CreateAccountPresenterImpl::class.java)
        mCreateAccountPresenter.initPresenter(this)
    }
    override fun navigateToHome() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}