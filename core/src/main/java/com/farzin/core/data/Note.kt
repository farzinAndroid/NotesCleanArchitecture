package com.farzin.core.data

data class Note(
    val id:Long = 0L,
    var title:String,
    var content:String,
    var creationTime:Long,
    var updateTime:Long,
    var wordCount:Int = 0
)
