package com.farzin.notescleanarchitecture.framework

import android.content.Context
import com.farzin.core.data.Note
import com.farzin.core.repository.NoteDataSource
import com.farzin.notescleanarchitecture.framework.db.DatabaseService
import com.farzin.notescleanarchitecture.framework.db.NoteEntity

class RoomNoteDataSource (context: Context): NoteDataSource{
    val noteDao= DatabaseService.getInstance(context).getNoteDao()

    override suspend fun addNote(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun removeNote(note: Note)  = noteDao.removeNoteEntity(NoteEntity.fromNote(note))

    override suspend fun getNote(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAllNotes() = noteDao.getAllNotesEntity().map { it.toNote() }

}