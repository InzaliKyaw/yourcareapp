package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.GeneralQuestionTemplateVO

@Dao
interface ConsultationRequestDao {
    @Query("SELECT * FROM ConsultationRequest")
    @ColumnInfo
    fun getAllConsultationRequest(): LiveData<List<ConsultationRequestVO>>

    @Query("SELECT * FROM ConsultationRequest WHERE id=:consultationId")
    fun getConsultationRequestWithId(consultationId:Long):LiveData<List<ConsultationRequestVO>>


    @Query("DELETE FROM ConsultationRequest")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllConsultationRequest(generalQuestinList:List<ConsultationRequestVO>)
}