package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.RelatedQuestionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RelatedQuestionTypeConverter {

    @TypeConverter
    fun toString(relatedQuestion: List<RelatedQuestionVO>): String {
        return Gson().toJson(relatedQuestion)
    }

    @TypeConverter
    fun toRelatedQuestionVO(relatedQuestionListJsonString: String): List<RelatedQuestionVO> {
        val relatedQuestionType = object : TypeToken<List<RelatedQuestionVO>>() {}.type
        return Gson().fromJson(relatedQuestionListJsonString, relatedQuestionType)
    }
}
