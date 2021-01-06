package com.example.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.GeneralQuestionTemplateVO

@Dao
interface GeneralQuestionTemplateDao {

    @Query("SELECT * FROM GeneralQuestionTemplate")
    @ColumnInfo
    fun getAllGeneralQuestion():LiveData<List<GeneralQuestionTemplateVO>>

    @Query("DELETE FROM GeneralQuestionTemplate")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGeneralQuestion(generalQuestinList:List<GeneralQuestionTemplateVO>)
}