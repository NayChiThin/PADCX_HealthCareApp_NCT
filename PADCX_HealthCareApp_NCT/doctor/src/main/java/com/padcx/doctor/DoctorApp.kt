package com.padcx.doctor

import android.app.Application
import com.padcx.shared.data.models.impls.ChatModelImpl
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.models.impls.PatientDetailModelImpl

class DoctorApp:Application() {
    override fun onCreate() {
        super.onCreate()
        HomeModelImpl.initDatabase(applicationContext)
        PatientDetailModelImpl.initDatabase(applicationContext)
        ChatModelImpl.initDatabase(applicationContext)
    }
}