package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.DoctorsVO

@Dao
interface DoctorDao {
    @Query("SELECT * FROM Doctors")
    @ColumnInfo
    fun getAllDoctorsList():LiveData<List<DoctorsVO>>

    @Query("SELECT * FROM Doctors WHERE email=:doctorEmail")
    @ColumnInfo
    fun getDoctorByEmail(doctorEmail:String):LiveData<List<DoctorsVO>>

    @Query("DELETE FROM Doctors")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertDoctor(doctorsVO: DoctorsVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertAllDoctors(doctorsVO: List<DoctorsVO>)
}