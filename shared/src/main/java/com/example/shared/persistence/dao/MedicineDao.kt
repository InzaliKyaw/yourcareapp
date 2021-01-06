package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.MedicineVO

@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    @ColumnInfo
    fun getAllMedicine(): LiveData<List<MedicineVO>>

    @Query("SELECT * FROM Medicine WHERE id=:medicineId")
    @ColumnInfo
    fun getMedicineWithId(medicineId:String): LiveData<List<MedicineVO>>

    @Query("SELECT * FROM Medicine WHERE pillName=:medicineName")
    @ColumnInfo
    fun getMedicineWithName(medicineName:String): LiveData<List<MedicineVO>>


    @Query("DELETE FROM Medicine")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMedicine(medicineList:List<MedicineVO>)
}