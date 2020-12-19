package com.padcx.padcx_healthcareapp_nct

import android.app.Application
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.models.impls.LoginModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl

class PatientApp:Application() {
    override fun onCreate() {
        super.onCreate()
        HomeModelImpl.initDatabase(applicationContext)
        RegisterModelImpl.initDatabase(applicationContext)
    }
}