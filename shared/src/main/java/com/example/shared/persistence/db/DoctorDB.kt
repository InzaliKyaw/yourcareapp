package com.example.shared.persistence.db

import android.content.Context
import androidx.room.*
import com.example.shared.data.vos.*
import com.example.shared.persistence.dao.*
import com.example.shared.persistence.typeconverters.*

@Database(
    entities = [DoctorsVO::class, SpecialitiesVO::class, GeneralQuestionTemplateVO::class,
        ConsultationRequestVO::class, MedicineVO::class,PatientsVO::class],
    version = 14,
    exportSchema = false
)
@TypeConverters(
    RelatedQuestionTypeConverter::class,
    MedicineTypeConverter::class,
    PatientTypeConverter::class,
    SpecialitiesTypeConverter::class,
    RoutineTypeConverter::class,
    DoctorTypeConverter::class
)
abstract class DoctorDB : RoomDatabase() {
    companion object {
        val DB_NAME = "DoctorDB"
        var dbInstance: DoctorDB? = null

        fun getDBInstance(context: Context): DoctorDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(
                        context, DoctorDB::class.java, DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun doctorDao(): DoctorDao
    abstract fun specialityDao(): SpecialityDao
    abstract fun generalQuestionTemplateDao(): GeneralQuestionTemplateDao
    abstract fun consultationRequestDao(): ConsultationRequestDao
    abstract fun medicineDao():MedicineDao
    abstract fun patientDao():PatientDao

}