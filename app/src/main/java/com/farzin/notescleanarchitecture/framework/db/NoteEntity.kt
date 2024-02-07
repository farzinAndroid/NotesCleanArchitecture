package com.farzin.notescleanarchitecture.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farzin.core.data.Note

@Entity("note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val title:String,
    val content:String,
    val creationTime:Long,
    val updateTime:Long,
){

    companion object{
        fun fromNote(note: Note) = NoteEntity(
            title = note.title,
            content = note.content,
            creationTime = note.creationTime,
            updateTime = note.updateTime,
            id = note.id
        )
    }

    fun toNote() = Note(id, title, content, creationTime, updateTime)

}
