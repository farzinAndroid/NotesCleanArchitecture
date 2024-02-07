package com.farzin.notescleanarchitecture.framework.di

import com.farzin.core.repository.NoteRepository
import com.farzin.core.usecase.AddNote
import com.farzin.core.usecase.GetAllNotes
import com.farzin.core.usecase.GetNote
import com.farzin.core.usecase.GetWordCount
import com.farzin.core.usecase.RemoveNote
import com.farzin.notescleanarchitecture.framework.db.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun providesUseCases(repository:NoteRepository) = UseCases(
        addNote = AddNote(repository),
        getNote = GetNote(repository),
        removeNote = RemoveNote(repository),
        getAllNotes = GetAllNotes(repository),
        getWordCount = GetWordCount()
    )

}