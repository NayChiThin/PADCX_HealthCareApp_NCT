package com.padcx.padcx_healthcareapp_nct.views.components

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.padcx.padcx_healthcareapp_nct.HomeFragment
import com.padcx.padcx_healthcareapp_nct.R
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.HomePresenter
import kotlinx.android.synthetic.main.confirm_consult_dialog.*

class CustomDialogConfirmRequest(context: Context, val view:HomeFragment, private val specialityName:String) : Dialog(context) {
    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.confirm_consult_dialog)

        tvCancel.setOnClickListener {
            dismiss()
        }
        btnConfirm.setOnClickListener {
            view.navigateToAddCaseSummary(specialityName)
        }
    }

}
