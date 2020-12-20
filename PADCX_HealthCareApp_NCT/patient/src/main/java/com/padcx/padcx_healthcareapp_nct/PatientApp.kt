package com.padcx.padcx_healthcareapp_nct

import android.app.Application
import com.padcx.shared.data.models.impls.*

class PatientApp:Application() {
    override fun onCreate() {
        super.onCreate()
        HomeModelImpl.initDatabase(applicationContext)
        RegisterModelImpl.initDatabase(applicationContext)
        ChatModelImpl.initDatabase(applicationContext)
        MessageModelImpl.initDatabase(applicationContext)
    }
}