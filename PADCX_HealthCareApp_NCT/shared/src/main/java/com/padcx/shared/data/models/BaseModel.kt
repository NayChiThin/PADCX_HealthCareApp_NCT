package com.padcx.shared.data.models

import android.content.Context
import com.padcx.shared.persistence.db.HealthCareDB

abstract class BaseModel {
    protected lateinit var mTheDB : HealthCareDB
    fun initDatabase(context: Context) {
        mTheDB = HealthCareDB.getDbInstance(context)
    }
}