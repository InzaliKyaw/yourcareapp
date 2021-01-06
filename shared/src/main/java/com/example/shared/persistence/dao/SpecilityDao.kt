package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.SpecialitiesVO

@Dao
interface SpecialityDao {
    @Query("SELECT * FROM Speciality")
    @ColumnInfo
    fun getAllSpeciality():LiveData<List<SpecialitiesVO>>

    @Query("SELECT * FROM Speciality WHERE id=:specialityId")
    fun getSpecialityWithId(specialityId:String):LiveData<List<SpecialitiesVO>>

    @Query("DELETE FROM Speciality")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertAllSpeciality(specialitiesVO: List<SpecialitiesVO>)
}