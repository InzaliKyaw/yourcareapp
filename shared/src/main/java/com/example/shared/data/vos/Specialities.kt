package com.example.shared.data.vos

data class Specialities(
    var id:String = "",
    var name:String = "",
    var relatedQuestion:RelatedQuestion = RelatedQuestion()
)