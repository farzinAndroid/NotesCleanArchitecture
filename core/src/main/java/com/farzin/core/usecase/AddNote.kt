package com.farzin.core.usecase

import com.farzin.core.data.Note
import com.farzin.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note:Note) = noteRepository.addNote(note)
}