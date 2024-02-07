package com.farzin.notescleanarchitecture.framework.db

import com.farzin.core.usecase.AddNote
import com.farzin.core.usecase.GetAllNotes
import com.farzin.core.usecase.GetNote
import com.farzin.core.usecase.GetWordCount
import com.farzin.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val removeNote: RemoveNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val getWordCount: GetWordCount,
)
