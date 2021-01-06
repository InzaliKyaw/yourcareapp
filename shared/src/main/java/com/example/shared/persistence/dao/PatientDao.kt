package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.PatientsVO

@Dao
interface PatientDao {

    @Query("SELECT * FROM Patient")
    @ColumnInfo
    fun getAllPatient(): LiveData<List<PatientsVO>>

    @Query("SELECT * FROM Patient WHERE email=:patientEmail")
    fun getPatientWithEmail(patientEmail:String): LiveData<List<PatientsVO>>

    @Query("SELECT * FROM Patient WHERE id=:patientId")
    fun getPatientById(patientId:Long): LiveData<List<PatientsVO>>

    @Query("DELETE FROM Patient")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPatients(patientList:List<PatientsVO>)
}