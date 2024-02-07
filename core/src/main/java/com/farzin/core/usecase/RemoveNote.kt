package com.farzin.core.usecase

import com.farzin.core.data.Note
import com.farzin.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note:Note) = noteRepository.removeNote(note)
}