package com.farzin.core.usecase

import com.farzin.core.data.Note
import com.farzin.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}