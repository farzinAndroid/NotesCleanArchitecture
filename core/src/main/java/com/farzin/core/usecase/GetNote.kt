package com.farzin.core.usecase

import com.farzin.core.data.Note
import com.farzin.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id:Long) = noteRepository.getNote(id)
}