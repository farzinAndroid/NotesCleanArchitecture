package com.farzin.notescleanarchitecture.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farzin.core.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(note: NoteEntity)

    @Delete
    suspend fun removeNoteEntity(note: NoteEntity)

    @Query("select * from note where id = :id")
    suspend fun getNoteEntity(id:Long): NoteEntity?

    @Query("select * from note")
    suspend fun getAllNotesEntity():List<NoteEntity>

}