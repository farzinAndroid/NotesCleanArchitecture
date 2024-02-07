package com.farzin.core.usecase

import com.farzin.core.data.Note

class GetWordCount {

    operator fun invoke(note:Note):Int{
        var wordCount:Int = 0
        wordCount += getWordCount(note.title)
        wordCount += getWordCount(note.content)
        return wordCount
    }


    fun getWordCount(text:String)=
        text.split(" ", "\n").count {
            it.contains(Regex(".*[a-zA-Z]*."))
        }

}