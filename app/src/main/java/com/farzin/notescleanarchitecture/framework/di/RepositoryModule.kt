package com.farzin.notescleanarchitecture.framework.di

import android.content.Context
import com.farzin.core.repository.NoteRepository
import com.farzin.notescleanarchitecture.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providesRepository(@ApplicationContext context: Context) =
        NoteRepository(RoomNoteDataSource(context))

}