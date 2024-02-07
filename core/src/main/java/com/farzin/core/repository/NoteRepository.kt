package com.farzin.core.repository

import com.farzin.core.data.Note

class NoteRepository(private val noteDataSource: NoteDataSource) {

    suspend fun addNote(note:Note) = noteDataSource.addNote(note)

    suspend fun removeNote(note: Note) = noteDataSource.removeNote(note)

    suspend fun getNote(id:Long) = noteDataSource.getNote(id)

    suspend fun getAllNotes() = noteDataSource.getAllNotes()

}