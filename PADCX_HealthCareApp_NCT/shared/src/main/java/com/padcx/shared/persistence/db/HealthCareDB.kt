package com.padcx.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcx.shared.data.vos.*
import com.padcx.shared.persistence.daos.*

@Database(
    entities = [DoctorVO::class,SpecialityVO::class,QuestionVO::class,PatientVO::class,
    ConsultVO::class,ConsultRequestVO::class,CheckoutVO::class,MessageVO::class,MedicineVO::class],
    version = 3,
    exportSchema = false
)
abstract class HealthCareDB :RoomDatabase(){
    companion object {
        val DB_NAME = "PADCX-HEALTHCARE.DB"
        var dbInstance : HealthCareDB? = null
        fun getDbInstance(context: Context):HealthCareDB {
            when(dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context,HealthCareDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }
    abstract fun specialityDao(): SpecialityDao
    abstract fun questionDao(): QuestionDao
    abstract fun patientDao(): PatientDao
    abstract fun doctorDao(): DoctorDao
    abstract fun consultDao(): ConsultDao
    abstract fun consultRequestDao(): ConsultRequestDao
    abstract fun checkoutDao() : CheckoutDao
    abstract fun messageDao() : MessageDao
    abstract fun medicineDao() : MedicineDao
}