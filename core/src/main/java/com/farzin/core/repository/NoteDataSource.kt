package com.farzin.core.repository

import com.farzin.core.data.Note

interface NoteDataSource {

    suspend fun addNote(note:Note)

    suspend fun removeNote(note: Note)

    suspend fun getNote(id:Long):Note?

    suspend fun getAllNotes():List<Note>

}